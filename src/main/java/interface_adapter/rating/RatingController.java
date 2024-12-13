package interface_adapter.rating;

import entity.Food;
import use_case.rating.RatingInputBoundary;
import use_case.rating.RatingInputData;
import view.RatingView;

public class RatingController {
    private final RatingInputBoundary ratingUseCaseInteractor;

    public RatingController(RatingInputBoundary ratingUseCaseInteractor) {
        this.ratingUseCaseInteractor = ratingUseCaseInteractor;
    }

    /**
     * Executes the Rating Use Case.
     * @param food The food being rated
     * @param rating The rating value provided by the user
     */
    public void execute(Food food, int rating) {
        RatingInputData ratingInputData = new RatingInputData(food, rating);

        ratingUseCaseInteractor.execute(ratingInputData);

    }
}
