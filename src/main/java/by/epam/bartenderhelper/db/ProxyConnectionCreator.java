package by.epam.bartenderhelper.db;

import static by.epam.bartenderhelper.util.PropertyManager.APP_CONFIG;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;


final class ProxyConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final String DRIVER_PROPERTY_NAME = "db.driver";
    private static final String URL_PROPERTY_NAME = "db.url";
    private static final String USER_PROPERTY_NAME = "db.user";
    private static final String PASSWORD_PROPERTY_NAME = "db.password";

    private static final String DB_DRIVER;
    private static final String DB_URL;
    private static final String DB_USER;
    private static final String DB_PASSWORD;

    static {
        DB_DRIVER = APP_CONFIG.getString(DRIVER_PROPERTY_NAME);
        DB_URL = APP_CONFIG.getString(URL_PROPERTY_NAME);
        DB_USER = APP_CONFIG.getString(USER_PROPERTY_NAME);
        DB_PASSWORD = APP_CONFIG.getString(PASSWORD_PROPERTY_NAME);
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Database driver not found {}", DB_DRIVER, e);
            throw new ExceptionInInitializerError(e);
        }
    }
    private ProxyConnectionCreator(){}

    static ProxyConnection create() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD));
    }
}
