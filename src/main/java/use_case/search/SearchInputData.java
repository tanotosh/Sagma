package use_case.search;

import entity.User;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {
    private final User user;
    private final String category;

    public SearchInputData(User user, String category) {
        this.user = user;
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public String getCategory() {
        return category;
    }
}
