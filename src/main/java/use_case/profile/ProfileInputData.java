package use_case.profile;

import entity.User;

/**
 * The Input Data for the Profile Use Case.
 */
public class ProfileInputData {

    private final User user;

    public ProfileInputData(User user) {
        this.user = user;
    }

    User getUser() {
        return user;
    }

}
