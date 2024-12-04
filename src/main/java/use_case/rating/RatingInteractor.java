package use_case.rating;

import entity.Food;

import use_case.uploadFood.UploadFoodInputBoundary;

/**
 * The Rating Interactor.
 */
public class RatingInteractor implements RatingInputBoundary {
    private final RatingOutputBoundary ratePresenter;

    public RatingInteractor(RatingOutputBoundary ratingOutputBoundary) {
        this.ratePresenter = ratingOutputBoundary;
    }

    @Override
    public void execute(RatingInputData ratingInputData) {
        Food food = ratingInputData.getFood();
        int rating = ratingInputData.getRating();




    }

    @Override
    public void switchToHomeView() {
        ratePresenter.switchToHomeView();
    }
}
