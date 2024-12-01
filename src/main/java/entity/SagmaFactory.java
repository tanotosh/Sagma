package entity;

import data_access.*;

import java.util.*;

public class SagmaFactory {
    //this Factory will be used to generate all our entities. We combined User Factory and Common user Factory into this.
    /* public User create(String name, String email, String password) {
        return new User(name, email, password);
    } */

    public User create(String name, String email, String password) {
        return new User(name, email, password);
    }

    // Create a Food
    public Food create(String name, User owner, int quantity, String ingredients, List<String> dietaryRestrictions,
                       String image_path, String category) {
        return new Food(name, owner, quantity, ingredients, dietaryRestrictions, image_path, category);
    }

    // Create DAOs (if needed, even though their methods are static)
    public UserDAO createUserDAO() {
        return new UserDAO(); // Useful if UserDAO needs future instantiation
    }

    public FoodDAO createFoodDAO() {
        return new FoodDAO();
    }

    public SwipeDAO createSwipeDAO() {
        return new SwipeDAO();
    }

    // Create DataManager
    public DataManager createDataManager() {
        return new DataManager();
    }
}

// do we need a factory?
