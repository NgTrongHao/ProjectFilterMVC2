/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.RegistrationCreationError;
import registration.RegistrationDAO;
import registration.RegistrationDTO;
import util.ApplicationConstants;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
@WebServlet(name = "AuthRegisterServlet", urlPatterns = {"/AuthRegisterServlet"})
public class AuthRegisterServlet extends HttpServlet {

    private final String USER_LENGTH_ERROR = "Username is required typing from 6 to 30 characters";
    private final String PASSWORD_LENGTH_ERROR = "Password is required typing from 6 to 30 characters";
    private final String CONFIRM_PASSWORD_ERROR = "Confirm must match Password";
    private final String FULLNAME_LENGTH_ERROR = "Fullname is required typing from 2 to 50 characters";
    private final String USER_EXISTED_ERROR = "Username is existed";

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

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");

        String url = ApplicationConstants.AuthRegisterFeatures.CREATE_ERROR_PAGE;

        //1. get all parameters
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");

        RegistrationCreationError errors = new RegistrationCreationError();
        boolean foundError = false;

        //2. call DAO
        //2.1 new DAO
        RegistrationDAO dao = new RegistrationDAO();

        try {
            //verify all user's error
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundError = true;
                errors.setUsernameLengthErr(USER_LENGTH_ERROR);
            } //2.2 call method of DAO (check existed username)
            else if (dao.checkExistedUsername(username)) {
                foundError = true;
                errors.setUsernameIsExisted(USER_EXISTED_ERROR);
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundError = true;
                errors.setPasswordLengthErr(PASSWORD_LENGTH_ERROR);
            } else if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                errors.setConfirmNotMatch(CONFIRM_PASSWORD_ERROR);
            }
            if (fullName.trim().length() < 2 || username.trim().length() > 50) {
                foundError = true;
                errors.setFullNameLengthErr(FULLNAME_LENGTH_ERROR);
            }

            if (foundError) {    //error occur
                //cache to attribute then forward page to display
                request.setAttribute("CREATE_ERRORS", errors);
            } else {    //no error
                //2.2 call method of DAO (regist new user)
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createNewAccount(dto);
                if (result) {
                    url = ApplicationConstants.AuthRegisterFeatures.LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            log("AuthRegisterServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AuthRegisterServlet_Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(siteMap.getProperty(url));
            rd.forward(request, response);
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
