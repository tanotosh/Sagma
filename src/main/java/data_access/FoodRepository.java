package data_access;

import entity.Food;

import java.util.List;

public interface FoodRepository {

    Food getFoodById(int id);

    void addFood(Food food);

    void addSwipeYes(int foodId, int userId);

    void addSwipeNo(int foodId, int userId);

}
