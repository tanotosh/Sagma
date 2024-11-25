package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:/Users/jiya/Desktop/CSC207/Sagma/src/schema.db";
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    // Method to establish a connection to the database
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
            logger.info("Connected to SQLite database!");
        } catch (Exception e) {
            logger.severe("Failed to connect to Database: " + e.getMessage());
        }
        return connection;
    }
}