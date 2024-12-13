package use_case.swiping;

import entity.Food;
import entity.User;

/**
 * The Input Data for the Swiping Use Case.
 */
public class SwipingInputData {
    private final User user;
    private final Food food;
    private final Boolean swipedYes;

    public SwipingInputData(User user, Food food, Boolean swipedYes) {
        this.user = user;
        this.food = food;
        this.swipedYes = swipedYes;
    }

    public User getUser() {
        return user;
    }

    public Food getFood() {
        return food;
    }

    public Boolean getSwipedYes() {
        return swipedYes;
    }
}
