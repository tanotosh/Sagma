package data_access;

import java.util.*;
import java.util.logging.Logger;
import entity.User;
import entity.Food;

public class DataManager {

    private static final Logger logger = Logger.getLogger(DataManager.class.getName());

    private List<User> users;   // Loaded users
    private List<Food> foods;   // Loaded foods

    public DataManager() {
        this.users = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

    public List<User> getUsers() { return users; }

    public List<Food> getFoods() { return foods; }

    // Add a new user
    public void addUser(User user) {
        users.add(user);            // Add to in-memory list
        UserDAO.addUser(user);      // Add to database
    }

    // Add a new food
    public void addFood(Food food) {
        foods.add(food);            // Add to in-memory list
        FoodDAO.addFood(food);      // Add to database
    }

    // Load all data from database into Java objects
    public void loadData() {
        users = UserDAO.getUsers();                 // Load all users
        foods = FoodDAO.getFoods();                 // Load all food items
        for (Food food : foods) {
            food.setSwipedYes(SwipeDAO.getSwipedYes(food.getFoodID()));
            food.setSwipedNo(SwipeDAO.getSwipedNo(food.getFoodID()));
        }
        logger.info("Data loaded successfully.");
    }

    // Save all changes back to the database
    public void saveData() {
        for (User user : users) {
            UserDAO.updateUser(user);               // Save user changes
        }

        for (Food food : foods) {
            if (food.getQuantity() > 0) {
                FoodDAO.updateFood(food.getFoodID(), food.getQuantity(), food.getRating(), food.getRatingsCount()); // Save food changes
            } else {
                FoodDAO.deleteFood(food.getFoodID()); // Remove food if quantity is zero
                SwipeDAO.deleteSwipe(food.getFoodID());
            }
        }
        logger.info("Data saved successfully.");
    }

    // Get a user by ID
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserID() == userId) {
                return user;
            }
        }
        return null;
    }

    // Get a food by ID
    public Food getFoodById(int foodId) {
        for (Food food : foods) {
            if (food.getFoodID() == foodId) {
                return food;
            }
        }
        return null;
    }

    // handle food upload
}
