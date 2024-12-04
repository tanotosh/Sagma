package use_case.rating;

import data_access.UserDAO;
import entity.Food;

import entity.User;
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

        User foodOwner = food.getOwner();
        float ownerRating = foodOwner.getRating();
        int ownerRatingsCount = foodOwner.getRatingsCount();
        float count = ownerRating * ownerRatingsCount;
        count = count + rating;
        ownerRatingsCount += 1;
        foodOwner.setRating(count/ownerRatingsCount);
        foodOwner.setRatingsCount(ownerRatingsCount);
        UserDAO.updateUser(foodOwner);

    }

    @Override
    public void switchToHomeView() {
        ratePresenter.switchToHomeView();
    }
}
