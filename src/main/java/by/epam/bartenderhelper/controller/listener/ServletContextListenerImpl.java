package by.epam.bartenderhelper.controller.listener;


import by.epam.bartenderhelper.model.pool.ConnectionPool;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import static by.epam.bartenderhelper.controller.command.ServletContextAttribute.*;


/**
 * The type Servlet context listener.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext= sce.getServletContext();
        servletContext.setAttribute(PAGINATION_ONE_PAGE_SIZE, DEFAULT_PAGINATION_ONE_PAGE_SIZE);
        servletContext.setAttribute(PAGINATION_PROFILE_ONE_PAGE_SIZE, DEFAULT_PAGINATION_PROFILE_ONE_PAGE_SIZE);
        servletContext.setAttribute(PAGINATION_NAV_LENGTH, DEFAULT_PAGINATION_NAV_LENGTH);
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
    }
}
