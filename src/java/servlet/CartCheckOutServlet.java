/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import cart.CartObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.OrderDAO;
import orderDetail.OrderDetailDAO;
import product.ProductDTO;
import util.ApplicationConstants;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "CartCheckOutServlet", urlPatterns = {"/CartCheckOutServlet"})
public class CartCheckOutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ApplicationConstants.CartCheckOutFeatures.VIEW_CART;

        try {
            //1. customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer get his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer get all items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        List<ProductDTO> products = (List<ProductDTO>) session.getAttribute("STORE");
                        if (products != null) {
                            OrderDAO orderDAO = new OrderDAO();
                            String orderId = orderDAO.createOrderID();
                            boolean insertOrderResult = orderDAO.insertOrder(orderId);
                            if (insertOrderResult) {
                                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                                int count = 0;
                                List<String> checkOutItems = null;
                                for (String key : items.keySet()) {
                                    ++count;
                                    for (ProductDTO product : products) {
                                        String productId = product.getProductID();
                                        if (key.equals(product.getProductName())) {
                                            if (items.get(key) <= product.getQuantity()) {
                                                boolean insertOrderDetailResult = orderDetailDAO.insertOrderDetail(count, productId, items.get(key), orderId);
                                                if (insertOrderDetailResult) {
                                                    if (checkOutItems == null) {
                                                        checkOutItems = new ArrayList<>();
                                                    }
                                                    checkOutItems.add(key);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (checkOutItems != null) {
                                    checkOutItems.forEach(item -> {
                                        items.remove(item);
                                    });
                                    if (items.isEmpty()) {
                                        url = ApplicationConstants.CartCheckOutFeatures.STORE_VIEW;
                                    }
                                }
                            }
                        }
                    }
                    session.setAttribute("CART", cart);
                }
            }
        } catch (SQLException ex) {
            log("CartCheckOutServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CartCheckOutServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
