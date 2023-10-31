/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ApplicationConstants;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "CartDeleteItemServlet", urlPatterns = {"/CartDeleteItemServlet"})
public class CartDeleteItemServlet extends HttpServlet {

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
        System.out.println("DeleteItemServlet");
        try {
            //1. customer goes to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer take his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer get items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. customer choose all removing items
                        String[] selectedItems = request.getParameterValues("checkItem");
                        if (selectedItems != null) {
                            for (String selectedItem : selectedItems) {
                                System.out.println(selectedItem);
                                cart.deleteItemsFromCart(selectedItem);
                            }
                            System.out.println("Cart: " + cart);
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }
        } finally {
            //refresh page --> call previous function (view cart) --> using url re-writing
            String url = ApplicationConstants.CartDeleteItemFeatures.VIEW_CART;
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
