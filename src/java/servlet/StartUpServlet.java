/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import registration.RegistrationDAO;
import registration.RegistrationDTO;
import util.ApplicationConstants;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

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

        String url = ApplicationConstants.StartUpFeatures.LOGIN_PAGE;
        try {
            HttpSession session = request.getSession(false);
            System.out.println(session);
            if (session != null) {
                //1. get all cookies
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    //2. get username and password from the newest cookies
                    Cookie newestCookie = cookies[cookies.length - cookies.length];
                    String username = newestCookie.getName();
                    String password = newestCookie.getValue();
                    //3. call DAO
                    //3.1 new DAO
                    RegistrationDAO dao = new RegistrationDAO();
                    //3.2 call method of DAO
                    RegistrationDTO dto = dao.checkLogin(username, password);
                    if (dto != null) {
                        session.setAttribute("USER_INFO", dto);
                        url = ApplicationConstants.StartUpFeatures.SEARCH_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            log("StartUpServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("StartUpServlet_Naming: " + ex.getMessage());
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
