package main.java.authorizationForm.controller.listener;

import main.java.authorizationForm.dao.DAOException;
import main.java.authorizationForm.dao.connectionpool.ConnectionPool;
import main.java.authorizationForm.dao.connectionpool.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerOfConnectionPool implements ServletContextListener {

    private Logger logger = LogManager.getLogger();

    public ListenerOfConnectionPool() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().poolInitialization();
        } catch (ConnectionPoolException e) {
            logger.log(Level.ERROR, "Connection pool didn't initialize.");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
