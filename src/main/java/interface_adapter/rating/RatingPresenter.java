package interface_adapter.rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.rating.RatingOutputBoundary;
import use_case.rating.RatingOutputData;

public class RatingPresenter implements RatingOutputBoundary {
    private RatingViewModel ratingViewModel;

    public RatingPresenter(RatingViewModel ratingViewModel) {
        this.ratingViewModel = ratingViewModel;
    }

    @Override
    public void presentRating(RatingOutputData outputData) {
        String s = " was successfully registered. They now have a rating of " + outputData.getRating();
        String message = "Your rating for " + outputData.getOwnerName() + "'s " + outputData.getFoodName() + s;

        ratingViewModel.setMessage(message);
    }

}
