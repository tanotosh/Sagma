package interface_adapter.session;

import entity.User;

/**
 * This class maintains the session state of a logged-in user.
 */
public class LoginSessionState {
    private static final LoginSessionState instance = new LoginSessionState();

    private int userID;        // Unique identifier for the logged-in user
    private String email;      // Email of the logged-in user
    private boolean isLoggedIn; // Flag indicating if the user is logged in

    /**
     * Set the logged-in user details using a User object.
     * @param user The logged-in User entity.
     */
    public void setLoggedInUser(User user) {
        this.userID = user.getUserID();
        this.email = user.getEmail();
        this.isLoggedIn = true; // Mark the user as logged in
    }

    // Clear session details
    public void clearSession() {
        this.userID = 0;
        this.email = null;
        this.isLoggedIn = false;
    }

    // Getters and setters
    public int getUserId() { return userID; }

    public void setUserId(int userId) { this.userID = userId; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isLoggedIn() { return isLoggedIn; }

    public void setLoggedIn(boolean loggedIn) { isLoggedIn = loggedIn;}

    // singleton access
    public static LoginSessionState getInstance() { return instance; }
}
