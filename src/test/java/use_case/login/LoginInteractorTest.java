package use_case.login;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import entity.User;
import interface_adapter.session.LoginSessionState;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {


    private UserDAO userDAO;
    private LoginSessionState sessionState;

    @BeforeEach
    void setUp() {
        // Initialize an empty UserDAO with hardcoded data
        DatabaseDAO DB = new DatabaseDAO();
        userDAO = new UserDAO(DB);
        sessionState = LoginSessionState.getInstance();
        sessionState.clearSession(); // Reset session state before each test

        // Add hardcoded users for testing
        userDAO.addUser(new User(1, "Paul", "paul@email.com", "password", 4.5f, 10, List.of("vegan"), null));
        userDAO.addUser(new User(2, "Alice", "alice@email.com", "alice123", 4.0f, 8, List.of("vegetarian"), null));
    }

    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("paul@email.com", "password");

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("paul@email.com", user.getEmail());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userDAO, sessionState);
        interactor.execute(inputData);
    }
    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("paul@email.com", "password");

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("paul@email.com", LoginSessionState.getInstance().getEmail());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(successPresenter, userDAO, sessionState);

        // Assert the session is initially empty
        assertFalse(LoginSessionState.getInstance().isLoggedIn());
        assertNull(LoginSessionState.getInstance().getEmail());

        // Execute the login
        interactor.execute(inputData);

        // Assert session state after login
        assertTrue(LoginSessionState.getInstance().isLoggedIn());
        assertEquals("paul@email.com", LoginSessionState.getInstance().getEmail());
    }


    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("paul@email.com", "wrong_password");

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid password.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(failurePresenter, userDAO, sessionState);
        interactor.execute(inputData);
    }

    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("nonexistent@email.com", "password");

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User not found.", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(failurePresenter, userDAO, sessionState);
        interactor.execute(inputData);
    }
}
