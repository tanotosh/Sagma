package data_access;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import entity.User;
import entity.Food;

public class FoodDAO {

    private static final Logger logger = Logger.getLogger(FoodDAO.class.getName());

    // Add a new food item to the database
    public static void addFood(Food food) {
        String sql = "INSERT INTO Foods (name, quantity, ingredients, dietary_restrictions, cuisine, owner_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, food.getName());
            stmt.setInt(2, food.getQuantity());
            stmt.setString(3, food.getIngredients());
            stmt.setString(4, food.getDietaryRestrictions());
            stmt.setString(5, food.getCuisine());
            stmt.setInt(6, food.getOwner().getUserId());
            stmt.executeUpdate();

            logger.info("Food item added successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to add food item: " + e.getMessage());
        }
    }

    // Get all food items from the database
    public static List<Food> getFoods() {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT food_id, name, quantity, ingredients, dietary_restrictions, cuisine, owner_id FROM Foods";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int foodId = rs.getInt("food_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String[] ingredients = rs.getString("ingredients").split(",");
                String[] dietaryRestrictions = rs.getString("dietary_restrictions").split(",");
                String cuisine = rs.getString("cuisine");
                int ownerId = rs.getInt("owner_id");

                // Fetch the User object for the owner
                User owner = UserDAO.getUserById(ownerId);

                // Fetch swipedYes and swipedNo lists
                List<User> swipedYes = SwipeDAO.getSwipedYes(foodId);
                List<User> swipedNo = SwipeDAO.getSwipedNo(foodId);

                // Create a Food object
                Food food = new Food(foodId, name, quantity, ingredients, dietaryRestrictions, cuisine, owner);
                foods.add(food);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch foods: " + e.getMessage());
        }

        return foods;
    }

    // Update an existing food item
    // Need function calls in the setter functions based on quantity (> 0)
    public static void updateFood(int foodId, int quantity) {
        String sql = "UPDATE Foods SET quantity = ? WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, quantity); // Bind the new quantity
            stmt.setInt(2, foodId);   // Bind the food ID
            stmt.executeUpdate();

            logger.info("Food quantity updated successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to update food quantity: " + e.getMessage());
        }
    }


    // Delete a food item
    // Need function calls in the setter functions based on quantity (== 0)
    public static void deleteFood(int foodId) {
        String sql = "DELETE FROM Foods WHERE food_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, foodId); // Bind the food ID
            stmt.executeUpdate();

            logger.info("Food item deleted successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to delete food item: " + e.getMessage());
        }
    }

    public static Food getFoodById(int foodId) {
        String sql = "SELECT food_id, name, quantity, ingredients, dietary_restrictions, cuisine, owner_id " +
                "FROM Foods WHERE food_id = ?";
        Food food = null;

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, foodId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String ingredients = rs.getString("ingredients");
                String dietaryRestrictions = rs.getString("dietary_restrictions");
                String cuisine = rs.getString("cuisine");
                int ownerId = rs.getInt("owner_id");

                User owner = UserDAO.getUserById(ownerId);

                List<User> swipedYes = SwipeDAO.getSwipedYes(foodId);
                List<User> swipedNo = SwipeDAO.getSwipedNo(foodId);

                food = new Food(owner, name, quantity, ingredients, dietaryRestrictions, swipedYes, swipedNo, cuisine);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch food by ID " + foodId + ": " + e.getMessage());
        }

        return food;
    }

}

