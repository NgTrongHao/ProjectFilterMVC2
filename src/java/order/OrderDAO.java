/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import util.DBHelper;
import util.RandomStringUtil;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class OrderDAO implements Serializable {

    public boolean insertOrder(String orderId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "INSERT INTO [Order] (id, orderDate, total) "
                        + "VALUES (?, GETDATE(), ?)";
                //3. create StatementObject
                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                stm.setFloat(2, 0);
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

    public String createOrderID() {
        return RandomStringUtil.randomAlphaNumeric(15);
    }
}
