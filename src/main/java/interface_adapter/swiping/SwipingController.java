package interface_adapter.swiping;

import entity.Food;
import entity.User;
import use_case.Swiping;
import use_case.rating.RatingInputBoundary;
import use_case.rating.RatingInputData;
import use_case.swiping.SwipingInputBoundary;
import use_case.swiping.SwipingInputData;

/**
 * The controller for the swiping Use Case.
 */
public class SwipingController {
    private final SwipingInputBoundary swipingUseCaseInteractor;

    public SwipingController(SwipingInputBoundary swipingUseCaseInteractor) {
        this.swipingUseCaseInteractor = swipingUseCaseInteractor;
    }

    /**
     * Executes the Swiping Use Case.
     * @param user The user that is swiping
     * @param food The food that the current user iss swiping on
     * @param swipedYes True if the user swiped yes on the food, false if swiped no
     */
    public void execute(User user, Food food, Boolean swipedYes) {
        SwipingInputData swipingInputData = new SwipingInputData(user, food, swipedYes);

        swipingUseCaseInteractor.execute(swipingInputData);

    }
}
