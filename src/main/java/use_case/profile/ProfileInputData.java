package use_case.profile;

import entity.User;

/**
 * The Input Data for the Profile Use Case.
 */
public class ProfileInputData {

    private final String email;

    public ProfileInputData(String email) {
        this.email = email;
    }

    String getEmail() {
        return email;
    }

}
