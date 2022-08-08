package edu.school21.cinema.listeners;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.cinema.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        sce.getServletContext().setAttribute("SpringContext", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext) sce.getServletContext().getAttribute("SpringContext");
        context.getBean(HikariDataSource.class).close();
        try{
            Enumeration<Driver> enumeration = DriverManager.getDrivers();
            while (enumeration.hasMoreElements()) {
                DriverManager.deregisterDriver(enumeration.nextElement());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
