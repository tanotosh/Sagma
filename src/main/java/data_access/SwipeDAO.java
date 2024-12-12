package data_access;

import entity.Food;
import entity.User;

import java.util.List;

public class SwipeDAO implements SwipeRepository {
    private final FoodRepository foodRepository;

    public SwipeDAO(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void addSwipeYes(int foodId, int userId) {
        foodRepository.addSwipeYes(foodId, userId);
    }

    @Override
    public void addSwipeNo(int foodId, int userId) {
        foodRepository.addSwipeNo(foodId, userId);
    }

    @Override
    public List<User> getSwipedYes(int foodId) {
        Food food = foodRepository.getFoodById(foodId);
        return food != null ? food.getSwipedYes() : List.of();
    }

    @Override
    public List<User> getSwipedNo(int foodId) {
        Food food = foodRepository.getFoodById(foodId);
        return food != null ? food.getSwipedNo() : List.of();
    }
}