package use_case.profile;

import data_access.UserDAO;
import entity.User;

/**
 * The Profile Interactor.
 */
public class ProfileInteractor implements ProfileInputBoundary {
    private final ProfileOutputBoundary profilePresenter;
    private final UserDAO userDAO;

    /**
     * Constructor for the ProfileInteractor.
     *
     * @param profilePresenter The presenter to handle output logic.
     * @param userDAO          The data access object for users.
     */
    public ProfileInteractor(ProfileOutputBoundary profilePresenter, UserDAO userDAO) {
        this.profilePresenter = profilePresenter;
        this.userDAO = userDAO;
    }

    @Override
    public void execute(ProfileInputData profileInputData) {
        final String email = profileInputData.getEmail();

        // Fetch user by email
        User user = userDAO.getUserByEmail(email);

        profilePresenter.prepareSuccessView(new ProfileOutputData(user.getCurrentFood().getName(), user.getMatches().toString()));
    }

}
