package interface_adapter.session;

import entity.User;

/**
 * This class manages session-related state during the signup process.
 */
public class SignupSessionState {
    private String username;
    private String email;
    private String password;
    private boolean signupSuccessful;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isSignupSuccessful() { return signupSuccessful; }
    public void setSignupSuccessful(boolean signupSuccessful) { this.signupSuccessful = signupSuccessful; }

    // Method to clear the session
    public void clearSession() {
        this.username = null;
        this.email = null;
        this.password = null;
        this.signupSuccessful = false;
    }

    // Method to set session details based on a User object
    public void setSignupDetails(User user) {
        this.username = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.signupSuccessful = true;
    }
}
