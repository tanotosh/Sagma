package data;

import data_access.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.connect()) {
            if (connection != null) {
                System.out.println("Connection successful!");

                // Test query
                String testQuery = "SELECT name FROM sqlite_master WHERE type='table';";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(testQuery);

                System.out.println("Tables in the database:");
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            } else {
                System.out.println("Failed to connect.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

