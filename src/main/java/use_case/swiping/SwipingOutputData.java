package use_case.swiping;

import entity.Food;
import entity.User;

public class SwipingOutputData {
    private User user; // the current user that is swiping
    private Food food; // the food that the user has matched with

    public SwipingOutputData(User user, Food food) {
        this.user = user;
        this.food = food;
    }
}
