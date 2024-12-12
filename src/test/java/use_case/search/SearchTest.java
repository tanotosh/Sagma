package use_case.search;

import entity.Food;
import entity.User;
import interface_adapter.state.SearchSessionState;
import use_case.search.SearchInteractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class MockPresenter implements SearchOutputBoundary {

    @Override
    public void prepareSuccessView(SearchOutputData results) {
        List<Food> expected = new ArrayList<>();
        expected.add(new Food("sushi", new User("sam", "sam@email.com", "password8"), 3, "seaweed", List.of("vegan"), null, "japanese"));
        expected.add(new Food("miso soup", new User("jackson", "jackson@email.com", "passwordd100"), 2, "tofu", List.of("vegan"), null, "japanese"));
        assertArrayEquals(results.getFilteredFoods().toArray(), expected.toArray());
    }
}

public class SearchTest {
    @BeforeEach
    void setUp() {
         SearchInteractor.mockdb= List.of(
                new Food("pasta", new User("jiya", "jiya@email.com", "password"), 3, "cheese", List.of("vegan"), null, "italian"),
                new Food("pizza", new User("gaia", "gaia@email.com", "passwordd"), 2, "cheese", List.of("vegatarian"), null, "italian"),
                new Food("rice cake", new User("ava", "ava@email.com", "password3"), 3, "gluten", List.of("vegan"), null, "korean"),
                new Food("kimbap", new User("ron", "ron@email.com", "passwordd5"), 2, "rice", List.of("vegatarian"), null, "korean"),
                new Food("sushi", new User("sam", "sam@email.com", "password8"), 3, "seaweed", List.of("vegan"), null, "japanese"),
                new Food("miso soup", new User("jackson", "jackson@email.com", "passwordd100"), 2, "tofu", List.of("vegan"), null, "japanese"));
    }

    @Test
    void search() {
        // Mock Presenter is used to check the resulting filtered list.
        SearchOutputBoundary mockPresenter = new MockPresenter();

        User currentUser = new User(1,"june", "june@email.com", "password", 5, 5, List.of("vegan"), null);

        SearchInteractor interactor = new SearchInteractor(mockPresenter);

        interactor.execute(new SearchInputData(currentUser, "japanese"));
    }
}