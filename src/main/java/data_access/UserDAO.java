package data_access;

import entity.User;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class UserDAO {

    public static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    // Add a new user
    public static void addUser(String name, String email, String password, float rating, int ratingsCount,
                               String dietaryRestrictions) {
        String sql = "INSERT INTO Users (name, email, password, rating, ratings_count, dietary_restrictions) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setFloat(4, rating);
            stmt.setInt(5, ratingsCount);
            stmt.setString(6, dietaryRestrictions);
            stmt.executeUpdate();
            logger.info("User added successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to add user: " + e.getMessage());
        }
    }

    // Get all users as User objects
    public static List<String> getUsers() {
        List<String> users = new ArrayList<>();
        String sql = "SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions FROM Users";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                float rating = rs.getFloat("rating");
                int ratingsCount = rs.getInt("ratings_count");
                String[] dietaryRestrictions = rs.getString("dietary_restrictions").split(",");

                User user = new User (userId, name, email, password, rating, ratingsCount,
                        dietaryRestrictions);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.severe("Failed to fetch users: " + e.getMessage());
        }
        return users;
    }

    // Need function calls in the setter functions
    public static void updateUser(User user) {
        String sql = "UPDATE Users SET name = ?, email = ?, password = ?, rating = ?, ratings_count = ?, " +
                "dietary_restrictions = ? WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the parameters based on the fields of the User object
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setFloat(4, user.getRating());
            stmt.setInt(5, user.getRatingsCount());
            stmt.setString(6, user.getDietaryRestrictions());
            stmt.setInt(7, user.getUserId());

            // Execute the update
            stmt.executeUpdate();
            logger.info("User updated successfully in the database.");

        } catch (SQLException e) {
            logger.severe("Failed to update user: " + e.getMessage());
        }
    }

    public static void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            logger.info("User deleted successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to delete user: " + e.getMessage());
        }
    }

    public static User getUserById(int userId) {
        String sql = "SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions FROM " +
                "Users WHERE user_id = ?";
        User user = null;

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, userId); // Bind the user_id parameter
            ResultSet rs = stmt.executeQuery();

            // If a result is found, construct the User object
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                float rating = rs.getFloat("rating");
                int ratingsCount = rs.getInt("ratings_count");
                String[] dietaryRestrictions = rs.getString("dietary_restrictions").split(",");

                // Create the User object
                user = new User(userId, name, email, password, rating, ratingsCount, dietaryRestrictions, this);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch user with ID " + userId + ": " + e.getMessage());
        }

        return user; // Returns null if no user is found
    }

}

