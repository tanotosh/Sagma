package interface_adapter.uploadFood;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.uploadFood.UploadFoodOutputBoundary;

/**
 * The Presenter for the Upload Food Use Case.
 */
public class UploadFoodPresenter implements UploadFoodOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public UploadFoodPresenter(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
