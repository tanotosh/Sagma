package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class ProfilePresenter implements ProfileOutputBoundary {

    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ProfilePresenter(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel = profileViewModel;
    }

    @Override
    public void prepareSuccessView(ProfileOutputData outputData) {
        return;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        return;
    }

    @Override
    public void presentProfileData(ProfileOutputData profileOutputData) {
        return;
    }
}
