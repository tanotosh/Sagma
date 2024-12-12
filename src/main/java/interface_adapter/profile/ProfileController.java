package interface_adapter.profile;

import entity.User;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

/**
 * The controller for the Profile Use Case.
 */
public class ProfileController {

    private final ProfileInputBoundary profileUseCaseInteractor;

    public ProfileController(ProfileInputBoundary profileUseCaseInteractor) {
        this.profileUseCaseInteractor = profileUseCaseInteractor;
    }

    /**
     * Executes the Profile Use Case.
     *
     * @param user the user logging in
     */
    public void execute(User user) {
        final ProfileInputData profileInputData = new ProfileInputData(user);

        profileUseCaseInteractor.execute(profileInputData);
    }
}
