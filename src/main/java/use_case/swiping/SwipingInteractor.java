package use_case.swiping;

import data_access.UserDAO;
import entity.Food;

import entity.User;
import use_case.uploadFood.UploadFoodInputBoundary;

/**
 * The Swiping Interactor.
 */
public class SwipingInteractor implements SwipingInputBoundary {
    private final SwipingOutputBoundary swipePresenter;

    public SwipingInteractor(SwipingOutputBoundary swipingOutputBoundary) {
        this.swipePresenter = swipingOutputBoundary;
    }

    @Override
    public void execute(SwipingInputData swipingInputData) {
        User user = swipingInputData.getUser();
        Food food = swipingInputData.getFood();
        Boolean swipedYes = swipingInputData.getSwipedYes();

        SwipingOutputData outputData = new SwipingOutputData(user, food);

        if (!swipedYes) {
            swipePresenter.prepareFailView(user);
        } else {
            if(food.getSwipedYes().contains(user)) {
                swipePresenter.prepareSuccessView(outputData);
            }
        }
    }

//    public void switchToMatchMadePage() {
//        swipePresenter.prepareSuccessView();

//    display next food: swipePresenter.prepareFailureView
    }
}
