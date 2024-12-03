package use_case.logout;

import data_access.UserDAO;
import entity.User;
import interface_adapter.state.LoginSessionState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    @Test
    void successTest() {
        // Prepare the test data
        LogoutInputData inputData = new LogoutInputData("paul@email.com");

        // Add a user to the database for testing
        User testUser = new User("Paul", "paul@email.com", "password");
        UserDAO.addUser(testUser); // Save the user in the database

        // Set the current user in the LoginSessionState
        LoginSessionState.getInstance().setLoggedInUser(testUser);

        // Create a success presenter to verify test case behavior
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                // Check that the output data contains the email of the user who logged out
                assertEquals("paul@email.com", user.getEmail());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };

        // Create and execute the interactor
        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter);
        interactor.execute(inputData);

        // Verify the user was logged out (session cleared)
        assertNull(LoginSessionState.getInstance().getEmail());
        assertFalse(LoginSessionState.getInstance().isLoggedIn());
    }

}