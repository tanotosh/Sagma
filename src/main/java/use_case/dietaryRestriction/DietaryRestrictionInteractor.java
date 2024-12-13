package use_case.dietaryRestriction;

import entity.User;

import java.util.List;

public class DietaryRestrictionInteractor implements DietaryRestrictionInputBoundary {
    private final DietaryRestrictionOutputBoundary presenter;

    public DietaryRestrictionInteractor(DietaryRestrictionOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute(DietaryRestrictionInputData inputData) {
        User user = inputData.getUser();
        String restriction = inputData.getRestriction();
        boolean isadd = inputData.isAdd();

        List<String> userRestrictions = user.getDietaryRestrictions();
        if (isadd) {
            if (!userRestrictions.contains(restriction)) {
            userRestrictions.add(restriction);
            }
        } else {
            userRestrictions.remove(restriction);
        }
        user.setDietaryRestrictions(userRestrictions);
    }

    @Override
    public void switchToHomeView() {
        presenter.switchToHomeView();
    }
}
