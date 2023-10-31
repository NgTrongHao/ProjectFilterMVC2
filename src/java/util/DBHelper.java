/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class DBHelper {

    public static Connection createConnection()
            throws NamingException, SQLException {
        //1. get current context
        Context currentContext = new InitialContext();
        //2. look up tomcat context
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        //3. look up datasource
        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DB");
        //4. open connection from DataSource
        Connection con = ds.getConnection();
        return con;
    }
}
