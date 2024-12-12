package interface_adapter.search;

import entity.User;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {
    private final SearchInputBoundary searchInteractor;

    public SearchController(SearchInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    /**
     * Executes the Search Use Case.
     *
     * @param category the selected category from the drop down menu
     * @param user current user
     */
    public void execute(User user, String category) {
        final SearchInputData searchInputData = new SearchInputData(user, category);

        searchInteractor.execute(searchInputData);
    }

}
