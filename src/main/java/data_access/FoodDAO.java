package data_access;

import java.util.*;

import entity.User;
import entity.Food;

import javax.xml.crypto.Data;

public class FoodDAO implements FoodRepository {
    private final List<Food> foods;
    private final List<User> users;
    UserDAO userDAO;

    public FoodDAO(DatabaseDAO DB) {
        this.foods = DB.getFOODS();
        this.users = DB.getUSERS();
        this.userDAO = new UserDAO(DB);
    }

    @Override
    public Food getFoodById(int id) {
        return foods.stream()
                .filter(food -> food.getFoodID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void addSwipeYes(int foodId, int userId) {
        Food food = getFoodById(foodId);
        User user = userDAO.getUserById(userId);
        if (food != null && user != null) {
            if (!food.getSwipedYes().contains(user)) {
                food.getSwipedYes().add(user);
            }
            food.getSwipedNo().remove(user);
        }
    }

    @Override
    public void addSwipeNo(int foodId, int userId) {
        Food food = getFoodById(foodId);
        User user = userDAO.getUserById(userId); // Delegating to UserDAO
        if (food != null && user != null) {
            if (!food.getSwipedNo().contains(user)) {
                food.getSwipedNo().add(user);
            }
            food.getSwipedYes().remove(user);
        }
    }

}