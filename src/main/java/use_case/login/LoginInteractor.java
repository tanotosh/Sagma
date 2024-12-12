package use_case.login;

import entity.User;
import data_access.UserDAO;
import interface_adapter.session.*;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginOutputBoundary loginPresenter;
    private final UserDAO userDAO;
    private final LoginSessionState sessionState;

    /**
     * Constructor for the LoginInteractor.
     *
     * @param userDAO          The data access object for users.
     * @param loginPresenter   The presenter to handle output logic.
     * @param sessionState     The session state for tracking logged-in users.
     */
    public LoginInteractor(LoginOutputBoundary loginPresenter, UserDAO userDAO, LoginSessionState sessionState) {
        this.loginPresenter = loginPresenter;
        this.userDAO = userDAO;
        this.sessionState = sessionState;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();

        // Fetch user by email
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            loginPresenter.prepareSuccessView(new LoginOutputData(false, "User not found."));
            return;
        }

        // Verify password
        if (!user.getPassword().equals(password)) {
            loginPresenter.prepareSuccessView(new LoginOutputData(false, "Invalid password."));
            return;
        }

        // Set session details
        LoginSessionState.getInstance().setLoggedInUser(user);

        loginPresenter.prepareSuccessView(new LoginOutputData(true, email));
    }

}
