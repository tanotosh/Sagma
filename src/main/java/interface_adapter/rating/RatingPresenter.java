package interface_adapter.rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.rating.RatingOutputBoundary;
import use_case.rating.RatingOutputData;

import javax.swing.*;

public class RatingPresenter implements RatingOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private String message; // Store the presentRating message

    public RatingPresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToHomeView() {

        // Show a pop-up with the presentRating message
        JOptionPane.showMessageDialog(null, message, "Rating Submitted", JOptionPane.INFORMATION_MESSAGE);


        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void presentRating(RatingOutputData outputData) {
        // Construct the message to display in the pop-up
        String s = " was successfully registered. They now have a rating of " + outputData.getRating();
        this.message = "Your rating for " + outputData.getOwnerName() + "'s " + outputData.getFoodName() + s;
    }
}
