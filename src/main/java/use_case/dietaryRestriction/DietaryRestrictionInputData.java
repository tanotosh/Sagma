package use_case.dietaryRestriction;

import entity.User;

public class DietaryRestrictionInputData {
    private final User user;
    private final String restriction;
    private final boolean add;

    public DietaryRestrictionInputData(User user, String restriction, boolean add) {
        this.user = user;
        this.restriction = restriction;
        this.add = add;
    }

    public User getUser() {
        return user;
    }

    public String getRestriction() {
        return restriction;
    }

    public boolean isAdd() {
        return add;
    }
}
