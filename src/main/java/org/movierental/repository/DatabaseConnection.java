package org.movierental.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/movierental";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Database connected");
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage());
        }
        return connection;
    }
}
