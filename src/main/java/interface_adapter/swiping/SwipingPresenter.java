package interface_adapter.swiping;

import entity.User;
import use_case.swiping.SwipingOutputBoundary;
import use_case.swiping.SwipingOutputData;

/**
 * The Presenter for the Swiping Use Case.
 */
public class SwipingPresenter implements SwipingOutputBoundary {

    private final SwipingViewModel swipingViewModel;

    public SwipingPresenter(SwipingViewModel swipingViewModel) {
        this.swipingViewModel = swipingViewModel;
    }

    @Override
    public void prepareSuccessView(SwipingOutputData data) {
        // On success, switch to match made page.

    }

    @Override
    public void prepareFailView(User user) {

    }

    @Override
    public void prepareFailView(String error) {
        // Display the next food
    }
}
