package data_access;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import entity.User;
import entity.Food;

public class SwipeDAO {

    private static final Logger logger = Logger.getLogger(SwipeDAO.class.getName());

    // SQL query constants
    private static final String INSERT_SWIPE =
            "INSERT INTO Swipes (user_id, food_id, is_right_swipe) VALUES (?, ?, ?)";
    private static final String SELECT_SWIPE_YES =
            "SELECT user_id FROM Swipes WHERE food_id = ? AND is_right_swipe = TRUE";
    private static final String SELECT_SWIPE_NO =
            "SELECT user_id FROM Swipes WHERE food_id = ? AND is_right_swipe = FALSE";
    private static final String SELECT_USER_SWIPES =
            "SELECT food_id FROM Swipes WHERE user_id = ?";
    private static final String DELETE_SWIPE =
            "DELETE FROM Swipes WHERE food_id = ?";

    public static void addSwipe(User user, Food food, boolean isRightSwipe) {
        addSwipe(user.getUserID(), food.getFoodID(), isRightSwipe); // Calls the ID-based method
    }

    // Add a swipe (right or left)
    public static void addSwipe(int userId, int foodId, boolean isRightSwipe) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(INSERT_SWIPE)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, foodId);
            stmt.setBoolean(3, isRightSwipe);
            stmt.executeUpdate();

            logger.info("Swipe added successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to add swipe: " + e.getMessage());
        }
    }

    // Get users who swiped right on a specific food (swipedYes)
    public static List<User> getSwipedYes(int foodId) {
        List<User> swipedYes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_SWIPE_YES)) {

            stmt.setInt(1, foodId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = UserDAO.getUserById(userId);
                swipedYes.add(user);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch users who swiped right: " + e.getMessage());
        }

        return swipedYes;
    }

    // Get users who swiped left on a specific food (swipedNo)
    public static List<User> getSwipedNo(int foodId) {
        List<User> swipedNo = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_SWIPE_NO)) {

            stmt.setInt(1, foodId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                User user = UserDAO.getUserById(userId); // Fetch user details
                swipedNo.add(user);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch users who swiped left: " + e.getMessage());
        }

        return swipedNo;
    }

    // Get all swipes made by a specific user
    public static List<Food> getUserSwipes(int userId) {
        List<Food> swipedFoods = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USER_SWIPES)) {

            stmt.setInt(1, userId); // Bind the user ID
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int foodId = rs.getInt("food_id");

                // Fetch the Food object using FoodDAO
                Food food = FoodDAO.getFoodById(foodId);
                if (food != null) {
                    swipedFoods.add(food);
                }
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch swiped foods for user: " + e.getMessage());
        }

        return swipedFoods;
    }

    // Delete all swipes for a specific food item when food is deleted
    public static void deleteSwipe(int foodId) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(DELETE_SWIPE)) {

            stmt.setInt(1, foodId); // Bind the food ID
            stmt.executeUpdate();

            logger.info("Deteled swipes for food!");

        } catch (SQLException e) {
            logger.severe("Failed to clear swipes for food ID " + foodId + ": " + e.getMessage());
        }
    }
}

