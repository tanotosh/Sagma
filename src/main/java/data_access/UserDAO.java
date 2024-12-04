package data_access;

import entity.Food;
import entity.User;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class UserDAO {

    public static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    // SQL Query Constants
    private static final String SELECT_USER_BY_ID =
            "SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions, current_food_id " +
                    "FROM Users WHERE user_id = ?";
    private static final String SELECT_ALL_USERS =
            "SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions, current_food_id " +
                    "FROM Users";
    private static final String SELECT_USER_BY_EMAIL =
            "SELECT user_id, name, email, password, rating, ratings_count, dietary_restrictions FROM Users " +
                    "WHERE email = ?";
    private static final String EXISTS_BY_EMAIL =
            "SELECT 1 FROM Users WHERE email = ?";
    private static final String INSERT_USER =
            "INSERT INTO Users (name, email, password, rating, ratings_count, dietary_restrictions, current_food_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER =
            "UPDATE Users SET rating = ?, ratings_count = ?, dietary_restrictions = ?, current_food_id = ? " +
                    "WHERE user_id = ?";

    public static void addUser(User user){
        addUser(user.getName(), user.getEmail(), user.getPassword(), user.getRating(), user.getRatingsCount(),
                user.getDietaryRestrictions(), user.getCurrentFood());
    }

    // Add a new user
    public static void addUser(String name, String email, String password, float rating, int ratingsCount,
                               List<String> dietaryRestrictions, Food food) {
        String sql = INSERT_USER;
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Validate the referenced food ID (currentFoodID)
            if (food == null) {
                throw new IllegalArgumentException("Referenced food does not exist.");
            }

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setFloat(4, rating);
            stmt.setInt(5, ratingsCount);
            stmt.setInt(6, food.getFoodID());

            String dietaryRestrictionsString = dietaryRestrictions != null ? String.join(",",
                    dietaryRestrictions) : "";
            stmt.setString(6, dietaryRestrictionsString);

            stmt.executeUpdate();
            logger.info("User added successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to add user: " + e.getMessage());
        }
    }

    // Get all users as User objects
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs)); // Use the helper function
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch users: " + e.getMessage());
        }

        return users;
    }

    public static void updateUser(User user) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_USER)) {

            stmt.setFloat(1, user.getRating());
            stmt.setInt(2, user.getRatingsCount());

            stmt.setString(3, String.join(",", user.getDietaryRestrictions()));

            // Handle current_food_id (null means no current food)
            stmt.setInt(4, user.getCurrentFood() != null ? user.getCurrentFood().getFoodID() : 0);

            stmt.setInt(5, user.getUserID());

            stmt.executeUpdate();
            logger.info("User updated successfully in the database.");

        } catch (SQLException e) {
            logger.severe("Failed to update user: " + e.getMessage());
        }
    }

    public static User getUserById(int userId) {
        User user = null;

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_ID)) {

            stmt.setInt(1, userId); // Bind the user_id parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = mapResultSetToUser(rs); // Use the helper function
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch user with ID " + userId + ": " + e.getMessage());
        }

        return user;
    }

    // Get a user by their email
    public static User getUserByEmail(String email) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch user by email: " + e.getMessage());
        }
        return null;
    }

    // Check if an email exists
    public static boolean existsByEmail(String email) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(EXISTS_BY_EMAIL)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            logger.severe("Failed to check if email exists: " + e.getMessage());
        }
        return false;
    }

    private static User mapResultSetToUser(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String password = rs.getString("password");
        float rating = rs.getFloat("rating");
        int ratingsCount = rs.getInt("ratings_count");
        String dietaryRestrictionsString = rs.getString("dietary_restrictions");

        // Convert dietaryRestrictionsString to a List
        List<String> dietaryRestrictions = dietaryRestrictionsString != null && !dietaryRestrictionsString.isEmpty()
                ? new ArrayList<>(Arrays.asList(dietaryRestrictionsString.split(",")))
                : new ArrayList<>();

        int currentFoodID = rs.getInt("current_food_id");

        // Fetch the currentFood using FoodDAO
        Food currentFood = null;
        if (currentFoodID != 0) {
            currentFood = FoodDAO.getFoodById(currentFoodID);
        }

        // Construct and return the User object
        return new User(userId, name, email, password, rating, ratingsCount, dietaryRestrictions, currentFood);
    }
}

