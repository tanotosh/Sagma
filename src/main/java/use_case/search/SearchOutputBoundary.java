package use_case.search;

import entity.Food;

import java.util.List;

/**
 * The output boundary for the Search Use Case.
 */

public interface SearchOutputBoundary {
    /**
     * Prepares the view to indicate a success.
     */
    void prepareSuccessView(SearchOutputData results);

}
