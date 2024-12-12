package use_case.swiping;

/**
 * Input Boundary for actions which are related to swiping on a food.
 */
public interface SwipingInputBoundary {

    /**
     * Executes the swiping use case.
     * @param swipingInputData the input data
     */
    void execute(SwipingInputData swipingInputData);

}
