package use_case.login;

import entity.User;
import data_access.UserDAO;
import interface_adapter.state.*;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    // private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginOutputBoundary loginOutputBoundary) {
        // this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();

        // Fetch user by email
        User user = UserDAO.getUserByEmail(email);

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

        loginPresenter.prepareSuccessView(new LoginOutputData(true, "Login successful. Welcome, " +
                user.getName() + "!"));
    }

}
