package interface_adapter.uploadFood;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.uploadFood.UploadFoodOutputBoundary;

/**
 * The Presenter for the Upload Food Use Case.
 */
public class UploadFoodPresenter implements UploadFoodOutputBoundary {
    private final UploadFoodViewModel uploadFoodViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;

    public UploadFoodPresenter(UploadFoodViewModel uploadFoodViewModel, ViewManagerModel viewManagerModel, HomeViewModel homeViewModel) {
        this.uploadFoodViewModel = uploadFoodViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void switchToHomeView() {
        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
