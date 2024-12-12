package use_case.logout;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import entity.User;
import interface_adapter.session.LoginSessionState;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

    private UserDAO userDAO;
    private LoginSessionState loginSessionState;

    @BeforeEach
    void setUp() {
        // Initialize the UserDAO and LoginSessionState
        DatabaseDAO DB = new DatabaseDAO();
        userDAO = new UserDAO(DB);
        loginSessionState = LoginSessionState.getInstance();

        // Prepare test user data
        User testUser = new User("Paul", "paul@email.com", "password");
        userDAO.addUser(testUser);

        // Set the test user as logged-in in the session state
        loginSessionState.setLoggedInUser(testUser);
    }


    @Test
    void successTest() {
        // Prepare the test data
        LogoutInputData inputData = new LogoutInputData("paul@email.com");

        // Create a success presenter to verify behavior
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData user) {
                // Verify the email of the logged-out user
                assertEquals("paul@email.com", user.getEmail());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };

        // Create the LogoutInteractor and execute the test
        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter, userDAO, loginSessionState);
        interactor.execute(inputData);

        // Verify that the session state remains cleared
        assertNull(loginSessionState.getUserId());
        assertNull(loginSessionState.getEmail());
    }

}