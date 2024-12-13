package use_case.swiping;

import entity.User;
import use_case.Swiping;

/**
 * The output boundary for the Swiping Use Case.
 */
public interface SwipingOutputBoundary {

    /**
     * Prepares the success view for the Swiping Use Case i.e the match made page
     * @param outputData the output data
     */
    void prepareSuccessView(SwipingOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case i.e displays next food
     * @param user The user that is still swiping
     */
    void prepareFailView(User user);

    void prepareFailView(String error);
}
