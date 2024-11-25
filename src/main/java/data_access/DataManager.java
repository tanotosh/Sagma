package data_access;

import java.util.*;
import entity.User;
import entity.Food;

public class DataManager {
    private List<User> users;   // Loaded users
    private List<Food> foods;        // Loaded foods

    public DataManager() {
        this.users = new ArrayList<>();
        this.foods = new ArrayList<>();
    }

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
            food.setSwipedYes(SwipeDAO.getSwipedYes(food.getFoodId()));
            food.setSwipedNo(SwipeDAO.getSwipedNo(food.getFoodId()));
        }
        System.out.println("Data loaded successfully.");
    }

    // Save all changes back to the database
    public void saveData() {
        for (User user : users) {
            UserDAO.updateUser(user);               // Save user changes
        }

        for (Food food : foods) {
            if (food.getQuantity() > 0) {
                FoodDAO.updateFood(food.getFoodId(), food.getQuantity()); // Save food changes
            } else {
                FoodDAO.deleteFood(food.getFoodId()); // Remove food if quantity is zero
                SwipeDAO.clearSwipesForFood(food.getFoodId());
            }
        }
        System.out.println("Data saved successfully.");
    }

    // Get a user by ID
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    // Get a food by ID
    public Food getFoodById(int foodId) {
        for (Food food : foods) {
            if (food.getFoodId() == foodId) {
                return food;
            }
        }
        return null;
    }
}
