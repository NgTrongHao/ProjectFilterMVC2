/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import util.DBHelper;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return this.accounts;
    }

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. Create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "And password = ?";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Querry
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    //mapping
                    //5.1 get data from ResultSet
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO
                    result = new RegistrationDTO(username, null, fullName, role);
                }//end username and password are existed
            }//end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void searchByFullName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname Like ?";
                //3. create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    //5.1 get data from ResultSet
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data into DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //5.3 add DTO into List
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. creare connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                //3. create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                int effectiveRows = stm.executeUpdate();
                if (effectiveRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Create Connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE Registration "
                        + "SET password = ?, "
                        + "isAdmin = ? "
                        + "WHERE username = ?";
                //3. Create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                //4. Execute Query
                int effectiveRows = stm.executeUpdate();
                //5. Process Result
                if (effectiveRows > 0) {
                    result = true;
                }
            }//end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createNewAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "INSERT INTO Registration (username, password, lastname, isAdmin) "
                        + "VALUES (?, ?, ?, ?)";
                //3. create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. execute query
                int effectiveRows = stm.executeUpdate();
                //5. process
                if (effectiveRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean checkExistedUsername(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DBHelper.createConnection();
            if (con != null) {
                String sql = "SELECT username "
                        + "FROM Registration "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
