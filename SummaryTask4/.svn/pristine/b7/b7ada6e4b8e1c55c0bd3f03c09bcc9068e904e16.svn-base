package ua.nure.thao.SummaryTask4.web.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListenerI18n implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
    	// no op
    }
	
    public void contextInitialized(ServletContextEvent event) {
    	// obtain file name with locales descriptions
    	ServletContext context = event.getServletContext();
    	String localesFileName = context.getInitParameter("locales");
    	
    	// obtain reale path on server
    	String localesFileRealPath = context.getRealPath(localesFileName);
    	
    	// locad descriptions
    	Properties locales = new Properties();
    	try {
			locales.load(new FileInputStream(localesFileRealPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// save descriptions to servlet context
    	context.setAttribute("locales", locales);
    	locales.list(System.out);
    }
	
}
