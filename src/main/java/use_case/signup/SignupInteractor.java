package use_case.signup;

import data_access.UserDAO;
import entity.User;
import entity.SagmaFactory;
import interface_adapter.state.SignupSessionState;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupOutputBoundary userPresenter;
    private final SignupSessionState signupSessionState = new SignupSessionState();
    // private final SagmaFactory sagmaFactory;

    public SignupInteractor(SignupOutputBoundary signupOutputBoundary) {
        // this.userDAO = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        // this.sagmaFactory = sagmaFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String email = signupInputData.getEmail();
        String password = signupInputData.getPassword();
        String repeatPassword = signupInputData.getRepeatPassword();

        // Check if the email already exists
        if (UserDAO.existsByEmail(email)) {
            userPresenter.prepareFailView("Email already exists.");
            return;
        }

        // Validate password match
        if (!password.equals(repeatPassword)) {
            userPresenter.prepareFailView("Passwords don't match.");
            return;
        }

        // Add user to the database
        User user = new User(username, email, password);
        UserDAO.addUser(user);

        signupSessionState.setSignupDetails(user); // Set session details
        SignupOutputData signupOutputData = new SignupOutputData(email, true);
        userPresenter.prepareSuccessView(signupOutputData);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
