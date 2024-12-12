package interface_adapter.dietaryRestriction;

import entity.Food;
import entity.User;
import use_case.dietaryRestriction.DietaryRestrictionInputBoundary;
import use_case.dietaryRestriction.DietaryRestrictionInputData;
import use_case.dietaryRestriction.DietaryRestrictionInteractor;
import use_case.rating.RatingInputBoundary;
import use_case.rating.RatingInputData;

public class DietaryRestrictionController {
    private final DietaryRestrictionInputBoundary dietaryRestrictionUseCaseInteractor;

    public DietaryRestrictionController(DietaryRestrictionInputBoundary dietaryRestrictionUseCaseInteractor) {
        this.dietaryRestrictionUseCaseInteractor = dietaryRestrictionUseCaseInteractor;
    }

    /**
     * Executes the Dietary Restriction Use Case.
     * @param user The user who's being altered
     * @param restriction The restriction being added or removed
     * @param add True if being added, false if being removed
     */
    public void execute(User user, String restriction, Boolean add) {
        DietaryRestrictionInputData dietaryRestrictionInputData = new DietaryRestrictionInputData(user, restriction, add);

        DietaryRestrictionInteractor.execute(dietaryRestrictionInputData);

    }
}
