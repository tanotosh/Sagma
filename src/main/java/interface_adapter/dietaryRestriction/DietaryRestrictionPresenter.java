package interface_adapter.dietaryRestriction;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.dietaryRestriction.DietaryRestrictionOutputBoundary;

public class DietaryRestrictionPresenter implements DietaryRestrictionOutputBoundary {
    private final DietaryRestrictionViewModel dietaryRestrictionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public DietaryRestrictionPresenter(DietaryRestrictionViewModel drViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.dietaryRestrictionViewModel = drViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
