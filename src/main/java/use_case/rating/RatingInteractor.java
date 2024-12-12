package use_case.rating;

import data_access.UserDAO;
import entity.Food;

import entity.User;
import use_case.uploadFood.UploadFoodInputBoundary;

/**
 * The Rating Interactor.
 */
public class RatingInteractor implements RatingInputBoundary {
    private final RatingOutputBoundary outputBoundary;

    public RatingInteractor(RatingOutputBoundary ratingOutputBoundary) {
        this.outputBoundary = ratingOutputBoundary;
    }

    @Override
    public void execute(RatingInputData ratingInputData) {
        Food food = ratingInputData.getFood();
        int rating = ratingInputData.getRating();

        User foodOwner = food.getOwner();
        float ownerRating = foodOwner.getRating();
        int ownerRatingsCount = foodOwner.getRatingsCount();
        float count = ownerRating * ownerRatingsCount;

        count += rating;
        ownerRatingsCount += 1;
        foodOwner.setRating(count/ownerRatingsCount);
        foodOwner.setRatingsCount(ownerRatingsCount);

        RatingOutputData outputData = new RatingOutputData(foodOwner.getRating(), foodOwner.getName(), food.getName());
        outputBoundary.presentRating(outputData);
        outputBoundary.switchToHomeView();


    } }
