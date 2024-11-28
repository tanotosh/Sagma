package use_case.signup;

import data_access.UserDAO;
import entity.User;
import entity.SagmaFactory;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {


    private static final String DB_URL = "jdbc:sqlite:test.db";

    @BeforeEach
    void setUp() throws Exception {
        // Reset the database before each test
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM Users");
        }
    }

    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Paul", "paul@email.com", "password",
                "password");

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // Verify the user exists in the database and matches the expected values
                User createdUser = UserDAO.getUserByEmail("paul@example.com");
                assertNotNull(createdUser);
                assertEquals("Paul", createdUser.getName());
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

        SignupInputBoundary interactor = new SignupInteractor(successPresenter);
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

        SignupInputBoundary interactor = new SignupInteractor(failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        // Add Paul to the database
        UserDAO.addUser(new User("Paul", "paul@example.com", "pwd"));

        SignupInputData inputData = new SignupInputData("Paul", "paul@email.cok", "password",
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

        SignupInputBoundary interactor = new SignupInteractor(failurePresenter);
        interactor.execute(inputData);
    }
}