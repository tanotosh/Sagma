package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     *
     * @param email the email of the user logging in
     */
    public void execute(String email, String password) {
        final LoginInputData loginInputData = new LoginInputData(email, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
