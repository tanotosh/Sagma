package data_access;

import entity.Food;
import entity.User;

import java.util.List;

public interface SwipeRepository {

    void addSwipeYes(int foodId, int userId);

    void addSwipeNo(int foodId, int userId);

    List<User> getSwipedYes(int foodId);

    List<User> getSwipedNo(int foodId);

}