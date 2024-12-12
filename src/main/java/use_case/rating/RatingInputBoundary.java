package use_case.rating;

/**
 * Input Boundary for actions which are related to rating a food.
 */
public interface RatingInputBoundary {

    /**
     * Executes the rating use case.
     * @param ratingInputData the input data
     */
    void execute(RatingInputData ratingInputData);

    /**
     * Executes the switch to home page
     */

}
