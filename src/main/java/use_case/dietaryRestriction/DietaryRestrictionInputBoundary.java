package use_case.dietaryRestriction;

import use_case.rating.RatingInputData;

public interface DietaryRestrictionInputBoundary {
    /**
     * Executes the Dietary Restriction use case.
     * @param dietaryRestrictionInputData the input data
     */
    void execute(DietaryRestrictionInputData dietaryRestrictionInputData);

    /**
     * Executes the switch to home page
     */
    void switchToHomeView();
}
