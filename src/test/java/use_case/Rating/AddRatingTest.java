package use_case.Rating;

import entity.Food;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.rating.RatingInputData;
import use_case.rating.RatingInteractor;
import use_case.rating.RatingOutputBoundary;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class AddRatingTest {

    private RatingInteractor interactor;
    private RatingOutputBoundary ratePresenter;

    // Hardcoded objects
    private User foodOwner;
    private Food food;

    @BeforeEach
    void setUp() {
        // Setup hardcoded User and Food objects
        foodOwner = new User(2, "Gaia", "gaia@email.com", "password", 5.0f, 5, Arrays.asList("vegan"), null);
        food = new Food("pasta", foodOwner, 3, "cheese", Arrays.asList("vegan"), null, "italian");

        // Initialize the interactor with a simple output boundary (we won't use it in this test)
        ratePresenter = new RatingOutputBoundary() {
            @Override
            public void switchToHomeView() {
                // Stub method for switching to the home view (no-op for testing)
            }
        };

        interactor = new RatingInteractor(ratePresenter);
    }

    @Test
    void addRatingTest() {
        // Rating input data
        RatingInputData inputData = new RatingInputData(food, 4); // User rates the food with a 4

        // Execute the interactor
        interactor.execute(inputData);

        // Assert the food owner's rating and ratings count
        assertEquals(4.833f, foodOwner.getRating(), 0.001, "Rating should be updated correctly.");
        assertEquals(6, foodOwner.getRatingsCount(), "Ratings count should increase.");
    }

    @Test
    void addRatingLowValueTest() {
        // User rates the food with a 1
        RatingInputData inputData = new RatingInputData(food, 1);

        // Execute the interactor
        interactor.execute(inputData);

        // Assert the food owner's rating and ratings count
        assertEquals(4.333f, foodOwner.getRating(), 0.001, "Rating should be updated correctly.");
        assertEquals(6, foodOwner.getRatingsCount(), "Ratings count should increase.");
    }

    @Test
    void addSameRatingTest() {
        // User rates the food with a 4
        RatingInputData inputData = new RatingInputData(food, 4);
        interactor.execute(inputData); // First rating

        // Execute again with the same rating
        interactor.execute(inputData); // Second rating

        // Assert the food owner's rating and ratings count
        assertEquals(4.714f, foodOwner.getRating(), 0.001, "Rating should remain the same.");
        assertEquals(7, foodOwner.getRatingsCount(), "Ratings count should remain the same.");
    }



}

