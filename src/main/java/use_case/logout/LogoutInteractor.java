package use_case.logout;

import data_access.UserDAO;
import interface_adapter.session.LoginSessionState;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutOutputBoundary logoutPresenter;
    UserDAO userDAO;
    LoginSessionState sessionState;

    public LogoutInteractor(LogoutOutputBoundary logoutOutputBoundary, UserDAO userDAO, LoginSessionState sessionState) {
        this.logoutPresenter = logoutOutputBoundary;
        this.userDAO = userDAO;
        this.sessionState = sessionState;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        String email = logoutInputData.getEmail();

        // Verify session state to ensure the logged-in user matches the email
        LoginSessionState sessionState = LoginSessionState.getInstance();
        if (sessionState.getEmail() != null && sessionState.getEmail().equals(email)) {
            // Clear session
            sessionState.clearSession();
            logoutPresenter.prepareSuccessView(new LogoutOutputData(email,false));
        } else {
            logoutPresenter.prepareFailView("Invalid email for the current session.");
        }
    }
}

