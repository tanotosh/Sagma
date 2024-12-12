package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.state.SearchSessionState;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, update the session state
        SearchSessionState sessionState = SearchSessionState.getInstance();
        sessionState.setFoods(response.getFilteredFoods());

        // Update the view manager to display the swiping view.
        this.viewManagerModel.setState("SwipingView");
        this.viewManagerModel.firePropertyChanged();
    }
}
