package interface_adapter.rating;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.rating.RatingOutputBoundary;

public class RatingPresenter implements RatingOutputBoundary {
    private final RatingViewModel ratingViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public RatingPresenter(RatingViewModel ratingViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.ratingViewModel = ratingViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
