package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.session.LoginSessionState;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, update the session state and switch to the logged-in view.
        LoginSessionState sessionState = LoginSessionState.getInstance();
        sessionState.setEmail(response.getEmail());
        sessionState.setLoggedIn(true);

        // Update the view manager to display the logged-in view.
        this.viewManagerModel.setState("loggedInView");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
