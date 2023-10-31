/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> products;

    public List<ProductDTO> getAllProducts() {
        return this.products;
    }

    public void loadProducts()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "SELECT productID, name, quantity, unitPrice, status "
                        + "FROM Product";
                //3. create StatementObject
                stm = con.prepareStatement(sql);
                //4. execute query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(productID, name, quantity, unitPrice, status);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    this.products.add(dto);
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
}
