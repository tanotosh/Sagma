package data_access;

import entity.User;
import entity.Food;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class DatabaseDAO implements DatabaseRepository{
    private static final Logger logger = Logger.getLogger(DatabaseDAO.class.getName());

    private List<User> USERS;
    private List<Food> FOODS;
    private final Map<Integer, User> userMap = new HashMap<>();
    private final Map<Integer, Food> foodMap = new HashMap<>();

    public List<User> getUSERS() {
        return USERS;
    }

    public List<Food> getFOODS() {
        return FOODS;
    }

    public DatabaseDAO() {
        this.USERS = new ArrayList<>();
        this.FOODS = new ArrayList<>();
    }

    public DatabaseDAO(boolean useDatabase) {
        loadData();
    }

    @Override
    public void loadData() {
        this.USERS = new ArrayList<>();
        this.FOODS = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect()) {
            logger.info("Loading data from the database...");
            loadUsers(connection);
            loadFoods(connection);
            assignSwipesToFoods(connection);
            logger.info("Data successfully loaded into memory.");
        } catch (SQLException e) {
            logger.severe("Error loading data from the database: " + e.getMessage());
        }
    }

    public void saveData() {
        try (Connection connection = DatabaseConnection.connect()) {
            logger.info("Saving data back to the database...");

            saveUsers(connection);
            saveFoods(connection);
            saveSwipes(connection);

            logger.info("Data saved to the database.");
        } catch (SQLException e) {
            logger.severe("Error saving data to the database: " + e.getMessage());
        }
    }

    // helper functions

    private void loadUsers(Connection connection) throws SQLException {
        String userQuery = "SELECT * FROM Users";
        try (PreparedStatement stmt = connection.prepareStatement(userQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                float rating = rs.getFloat("rating");
                int ratingsCount = rs.getInt("ratings_count");

                String dietaryRestrictionsString = rs.getString("dietary_restrictions");
                List<String> dietaryRestrictions = dietaryRestrictionsString != null && !dietaryRestrictionsString.isEmpty()
                        ? List.of(dietaryRestrictionsString.split(","))
                        : new ArrayList<>();

                User user = new User(userID, name, email, password, rating, ratingsCount, dietaryRestrictions, null);
                USERS.add(user);
                userMap.put(userID, user);
            }
            logger.info("Users loaded into memory.");
        }
    }

    private void loadFoods(Connection connection) throws SQLException {
        String foodQuery = "SELECT * FROM Foods";
        try (PreparedStatement stmt = connection.prepareStatement(foodQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int foodID = rs.getInt("food_id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String ingredients = rs.getString("ingredients");

                String dietaryRestrictionsString = rs.getString("dietary_restrictions");
                List<String> dietaryRestrictions = dietaryRestrictionsString != null && !dietaryRestrictionsString.isEmpty()
                        ? List.of(dietaryRestrictionsString.split(","))
                        : new ArrayList<>();

                String category = rs.getString("category");
                String imagePath = rs.getString("image_path");
                int ownerId = rs.getInt("owner_id");

                User owner = userMap.get(ownerId);

                Food food = new Food(name, owner, quantity, ingredients, dietaryRestrictions, imagePath, category);
                food.setFoodID(foodID);
                FOODS.add(food);
                foodMap.put(foodID, food);
            }
            logger.info("Foods loaded into memory.");
        }
    }

    private void assignSwipesToFoods(Connection connection) throws SQLException {
        String swipeQuery = "SELECT * FROM Swipes";
        try (PreparedStatement stmt = connection.prepareStatement(swipeQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int foodID = rs.getInt("food_id");
                int userID = rs.getInt("user_id");
                boolean isRightSwipe = rs.getBoolean("is_right_swipe");

                User user = userMap.get(userID);
                Food food = foodMap.get(foodID);

                if (food != null && user != null) {
                    if (isRightSwipe) {
                        food.getSwipedYes().add(user);
                    } else {
                        food.getSwipedNo().add(user);
                    }
                }
            }
            logger.info("Swipes assigned to foods.");
        }
    }

    private void saveUsers(Connection connection) throws SQLException {
        String updateUserQuery = "UPDATE Users SET dietary_restrictions = ?, current_food_id = ?, rating = ?, ratings_count = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateUserQuery)) {
            for (User user : USERS) {
                stmt.setString(1, String.join(",", user.getDietaryRestrictions()));
                stmt.setInt(2, user.getCurrentFood() != null ? user.getCurrentFood().getFoodID() : 0);
                stmt.setFloat(3, user.getRating());
                stmt.setInt(4, user.getRatingsCount());
                stmt.setInt(5, user.getUserID());
                stmt.executeUpdate();
            }
            logger.info("Users saved to the database.");
        }
    }

    private void saveFoods(Connection connection) throws SQLException {
        String updateFoodQuery = "UPDATE Foods SET quantity = ? WHERE food_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateFoodQuery)) {
            for (Food food : FOODS) {
                stmt.setInt(1, food.getQuantity());
                stmt.setInt(2, food.getFoodID());
                stmt.executeUpdate();
            }
            logger.info("Foods saved to the database.");
        }
    }

    private void saveSwipes(Connection connection) throws SQLException {
        String deleteSwipesQuery = "DELETE FROM Swipes";
        String insertSwipeQuery = "INSERT INTO Swipes (swipe_id, user_id, food_id, is_right_swipe) VALUES (?, ?, ?, ?)";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSwipesQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertSwipeQuery)) {

            deleteStmt.executeUpdate(); // Clear all existing swipes

            int swipeId = 1;
            for (Food food : FOODS) {
                for (User user : food.getSwipedYes()) {
                    insertStmt.setInt(1, swipeId++);
                    insertStmt.setInt(2, user.getUserID());
                    insertStmt.setInt(3, food.getFoodID());
                    insertStmt.setBoolean(4, true);
                    insertStmt.executeUpdate();
                }
                for (User user : food.getSwipedNo()) {
                    insertStmt.setInt(1, swipeId++);
                    insertStmt.setInt(2, user.getUserID());
                    insertStmt.setInt(3, food.getFoodID());
                    insertStmt.setBoolean(4, false);
                    insertStmt.executeUpdate();
                }
            }
            logger.info("Swipes saved to the database.");
        }
    }
}
