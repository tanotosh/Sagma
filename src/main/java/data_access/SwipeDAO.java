package data_access;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import entity.User;
import entity.Food;

public class SwipeDAO {

    private static final Logger logger = Logger.getLogger(SwipeDAO.class.getName());

    public static void addSwipe(User user, Food food, boolean isRightSwipe) {
        addSwipe(user.getUserId(), food.getFoodId(), isRightSwipe); // Calls the ID-based method
    }

    // Add a swipe (right or left)
    public static void addSwipe(int userId, int foodId, boolean isRightSwipe) {
        String sql = "INSERT INTO Swipes (user_id, food_id, is_right_swipe) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        String sql = "SELECT user_id FROM Swipes WHERE food_id = ? AND is_right_swipe = TRUE";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        String sql = "SELECT user_id FROM Swipes WHERE food_id = ? AND is_right_swipe = FALSE";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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
        String sql = "SELECT food_id FROM Swipes WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

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

    // Delete all swipes for a specific food item
    public static void deleteSwipe(int foodId) {
        String sql = "DELETE FROM Swipes WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, foodId); // Bind the food ID
            stmt.executeUpdate();

            logger.info("Deteled swipes for food ID: " + foodId);

        } catch (SQLException e) {
            logger.severe("Failed to clear swipes for food ID " + foodId + ": " + e.getMessage());
        }
    }
}

