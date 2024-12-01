package data_access;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import entity.User;
import entity.Food;

public class FoodDAO {

    private static final Logger logger = Logger.getLogger(FoodDAO.class.getName());

    // SQL Query Constants
    private static final String INSERT_FOOD =
            "INSERT INTO Foods (name, quantity, ingredients, dietary_restrictions, category, owner_id, image_path) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_FOODS =
            "SELECT food_id, name, quantity, ingredients, dietary_restrictions, category, owner_id, image_path FROM " +
                    "Foods";
    private static final String SELECT_FOOD_BY_ID =
            "SELECT food_id, name, quantity, ingredients, dietary_restrictions, category, owner_id, image_path FROM " +
                    "Foods WHERE food_id = ?";
    private static final String UPDATE_FOOD =
            "UPDATE Foods SET quantity = ?, rating = ?, ratings_count = ? WHERE food_id = ?";
    private static final String DELETE_FOOD =
            "DELETE FROM Foods WHERE food_id = ?";
    
    // Add a new food item to the database
    public static void addFood(Food food) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(INSERT_FOOD)) {

            stmt.setString(1, food.getName());
            stmt.setInt(2, food.getQuantity());
            stmt.setString(3, food.getIngredients());
            stmt.setString(4, String.join(",", food.getDietaryRestrictions()));
            stmt.setString(5, food.getCategory());
            stmt.setInt(6, food.getOwner().getUserID());
            stmt.setString(7, food.getImagePath());
            stmt.executeUpdate();

            logger.info("Food item added successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to add food item: " + e.getMessage());
        }
    }

    // Get all food items from the database
    public static List<Food> getFoods() {
        List<Food> foods = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_FOODS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                foods.add(mapResultSetToFood(rs));
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch foods: " + e.getMessage());
        }

        return foods;
    }

    // Update an existing food item
    // Need function calls in the setter functions based on quantity (> 0)
    public static void updateFood(int foodId, int quantity, float rating, int ratingsCount) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_FOOD)) {

            stmt.setInt(1, quantity);
            stmt.setFloat(2, rating);
            stmt.setInt(3, ratingsCount);
            stmt.setInt(4, foodId);
            stmt.executeUpdate();

            logger.info("Food quantity updated successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to update food quantity: " + e.getMessage());
        }
    }

    // Delete a food item
    // Need function calls in the setter functions based on quantity (== 0)
    public static void deleteFood(int foodID) {

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(DELETE_FOOD)) {

            stmt.setInt(1, foodID);
            stmt.executeUpdate();

            logger.info("Food item deleted successfully.");

        } catch (SQLException e) {
            logger.severe("Failed to delete food item: " + e.getMessage());
        }
    }

    public static Food getFoodById(int foodID) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(SELECT_FOOD_BY_ID)) {

            stmt.setInt(1, foodID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToFood(rs);
            }

        } catch (SQLException e) {
            logger.severe("Failed to fetch food by ID " + foodID + ": " + e.getMessage());
        }

        logger.warning("No food found!");
        return null;
    }

    private static Food mapResultSetToFood(ResultSet rs) throws SQLException {
        int foodId = rs.getInt("food_id");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        String ingredients = rs.getString("ingredients");
        String dietaryRestrictionsString = rs.getString("dietary_restrictions");
        String category = rs.getString("category");
        int ownerId = rs.getInt("owner_id");
        String image_path = rs.getString("image_path");

        // Convert dietary restrictions back to a List
        List<String> dietaryRestrictions = dietaryRestrictionsString != null && !dietaryRestrictionsString.isEmpty()
                ? Arrays.asList(dietaryRestrictionsString.split(","))
                : new ArrayList<>();

        User owner = UserDAO.getUserById(ownerId);
        List<User> swipedYes = SwipeDAO.getSwipedYes(foodId);
        List<User> swipedNo = SwipeDAO.getSwipedNo(foodId);

        Food food = new Food(name, owner, quantity, ingredients, dietaryRestrictions, image_path, category);
        food.setSwipedYes(swipedYes);
        food.setSwipedNo(swipedNo);

        return food;
    }

}

