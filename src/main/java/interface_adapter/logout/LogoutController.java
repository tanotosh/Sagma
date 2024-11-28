package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use case.
 */
public class LogoutController {

    private final LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param email the username of the user logging in
     */
    public void execute(String email) {
        // Create empty LogoutInputData and trigger the use case
        logoutUseCaseInteractor.execute(new LogoutInputData(email));
    }
}
