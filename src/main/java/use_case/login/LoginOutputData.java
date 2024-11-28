package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String email;
    private final boolean useCaseFailed;

    public LoginOutputData(boolean useCaseFailed, String email) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }
}
