package use_case.profile;

import data_access.UserDAO;
import entity.User;

/**
 * The Profile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary profilePresenter;

    public ProfileInteractor(ProfileOutputBoundary profileOutputBoundary) {
        this.profilePresenter = profileOutputBoundary;
    }

    @Override
    public void execute(ProfileInputData profileInputData) {
        User profileUser = profileInputData.getUser();
        String userFood = profileUser.getCurrentFood().getName();
        String userMatches = profileUser.getMatches().toString();

        ProfileOutputData profileOutputData = new ProfileOutputData(userFood, userMatches);

        profilePresenter.presentProfileData(profileOutputData);
    }

}
