package use_case.login;

import data_access.UserDAO;
import entity.SagmaFactory;
import entity.User;
import interface_adapter.state.LoginSessionState;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private static final String TEST_DB_URL = "jdbc:sqlite:test.db";

    @BeforeAll
    static void setupTestDatabase() {
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             Statement statement = connection.createStatement()) {
            // Create the test schema
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                            "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT NOT NULL," +
                            "email TEXT UNIQUE NOT NULL," +
                            "password TEXT NOT NULL," +
                            "rating FLOAT DEFAULT 0," +
                            "ratings_count INTEGER DEFAULT 0," +
                            "dietary_restrictions TEXT," +
                            "current_food_id INTEGER DEFAULT 0" +
                            ");"
            );

            statement.executeUpdate("DELETE FROM Users;"); // Clean slate
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up test database", e);
        }
    }

    @BeforeEach
    void populateDatabase() {
        UserDAO.addUser("Paul", "paul@email.com", "password", 4.5f, 10,
                null);
    }

    @AfterEach
    void clearDatabase() {
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Users;");
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear test database", e);
        }
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

        LoginInputBoundary interactor = new LoginInteractor(successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("paul@email.com", "password");

        // Populate the database with the test user
        UserDAO.addUser("Paul", "paul@email.com", "password", 4.5f, 10,
                null);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // Assert that the user's username matches the one in the database
                assertEquals("paul@email.com", LoginSessionState.getInstance().getEmail());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(successPresenter);

        assertFalse(LoginSessionState.getInstance().isLoggedIn());
        assertNull(LoginSessionState.getInstance().getUsername());

        interactor.execute(inputData);

        assertTrue(LoginSessionState.getInstance().isLoggedIn());
        assertEquals("Paul", LoginSessionState.getInstance().getUsername());
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

        LoginInputBoundary interactor = new LoginInteractor(failurePresenter);
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

        LoginInputBoundary interactor = new LoginInteractor(failurePresenter);
        interactor.execute(inputData);
    }
}
