package use_case.search;

import entity.Food;

import java.util.List;

/**
 * Input Boundary for actions which are related searching.
 */
public interface SearchInputBoundary {
    /**
     * Executes the search use case.
     *
     * @param searchInputData the input data
     */
    void execute(SearchInputData searchInputData);
}
