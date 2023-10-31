/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package listener;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import util.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author ngtronghao <ngtronghao02@gmail.com>
 */
public class ContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //1. get current context
            ServletContext context = sce.getServletContext();
            //2. get sitemap file
            String siteMapsFile = context.getInitParameter("SITEMAPS_FILE_PATH");
            //3. load properties
            Properties siteMap = PropertiesFileHelper.getProperties(context, siteMapsFile);
            //4. store siteMap to Context Attribute
            context.setAttribute("SITEMAPS", siteMap);
        } catch (IOException ex) {
            Logger.getLogger("ContextServletListener_IO: " + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
