/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;

/**
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class PropertiesFileHelper {

    public static Properties getProperties(ServletContext context, String fileRelativePath)
            throws IOException {
        InputStream input = context.getResourceAsStream(fileRelativePath);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }
}
