package use_case.signup;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import entity.User;
import interface_adapter.session.SignupSessionState;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    private DatabaseDAO DB = new DatabaseDAO();
    private UserDAO userDAO = new UserDAO(DB);

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "paul@email.com", "password",
                "password");

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // Verify the user exists in the database and matches expected values
                User createdUser = userDAO.getUserByEmail("paul@email.com");
                assertNotNull(createdUser);
                assertEquals("Paul", createdUser.getName());
                assertEquals("paul@email.com", createdUser.getEmail());
                assertEquals("password", createdUser.getPassword());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(successPresenter, userDAO, new SignupSessionState());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        SignupInputData inputData = new SignupInputData("Paul", "paul@email.com", "password",
                "wrong");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(failurePresenter, userDAO, new SignupSessionState());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        // Add Paul to the database
        userDAO.addUser(new User("Paul", "paul@email.com", "pwd"));

        SignupInputData inputData = new SignupInputData("Paul", "paul@email.com", "password",
                "password");

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                // This is expected
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(failurePresenter, userDAO, new SignupSessionState());
        interactor.execute(inputData);
    }
}