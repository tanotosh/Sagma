package use_case;

import entity.User;

import java.util.List;

public class DietaryRestriction {

    private User user;
    private String dietaryRestriction;

    public DietaryRestriction(User user, String dietaryRestriction) {
        this.user = user;
        this.dietaryRestriction = dietaryRestriction;
    }

    public void addRestriction() {
        List<String> userRestrictions = user.getDietaryRestrictions();
        userRestrictions.add(dietaryRestriction);
        user.setDietaryRestrictions(userRestrictions);
    }

    public void removeRestriction() {
        List<String> userRestrictions = user.getDietaryRestrictions();
        userRestrictions.remove(dietaryRestriction);
        user.setDietaryRestrictions(userRestrictions);
    }

}
