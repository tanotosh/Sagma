//package use_case.Rating;
//
//import data_access.UserDAO;
//import data_access.FoodDAO;
//import entity.User;
//import entity.Food;
//import interface_adapter.state.LoginSessionState;
//import org.junit.jupiter.api.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AddRatingTest {
//
//    private static final String DB_URL = "jdbc:sqlite:test.db";
//
//    // this will be the code that all test cases need to copy to reset the test database to the original 5
//    @BeforeEach
//    void setUp() throws Exception {
//        // populate database with data before each test
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement stmt = connection.createStatement()) {
//            // can add less values like for rating the execute statement could look like this:
//            // stmt.execute("INSERT into User (user_id, rating, ratings_count, current_food_id) VALUES (1,'jiya', 5," +
//            //                    " 5, 1) ");
//            // the statement will only populate the attributes / columns that are needed for testing specific use cases
//            // this stays true for food and swipes table as well
//            stmt.execute("INSERT into User (user_id, name, email, password, rating, ratings_count, " +
//                    "dietary_restrictions, current_food_id) VALUES " +
//                    "(1,'jiya', 'jiya@email.com', 'password', 5, 5, 'vegetarian', 1)," +
//                    "(2,'gaia', 'gaia@email.com', 'passwordd', 5, 5,'vegan', 2)");
//
//            stmt.execute("INSERT into Foods (food_id, name, quantity, ingredients, dietary_restrictions, cuisine, " +
//                    "owner_id, image_path) VALUES " +
//                    "(1, 'pizza', 2, 'cheese', 'vegetarian', 'italian', 1, NULL)," +
//                    "(2, 'pasta', 3, 'cheese', 'vegan', 'italian', 2, NULL)");
//
//            stmt.execute("INSERT into Swipes (swipe_id, user_id, food_id, is_right_swipe) VALUES " +
//                    "(1, 1, 2, 1), " +
//                    "(2, 2, 1, 1)");
//        }
//
//    }
//
//    @Test
//    void addRatingTest() {
//        User jiya  = UserDAO.getUserById(1);
//        Food pasta = FoodDAO.getFoodById(2);
//        jiya.rateFood(pasta, 2);
//        User gaia = UserDAO.getUserById(2);
//        float gaiaNewRating = gaia.getRating();
//        assertEquals(gaiaNewRating, 4.5);
//        float gaiaNewRatingsCount = gaia.getRatingsCount();
//        assertEquals(gaiaNewRatingsCount, 6);
//
//    }
//
//
//    // adding this so that at the end of all tests, the database is empty
//    @AfterAll
//    static void tearDownAll() throws Exception {
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement stmt = connection.createStatement()) {
//
//            // Delete from parent tables; cascading will clean up child tables
//            stmt.execute("DELETE FROM Users;");
//            stmt.execute("DELETE FROM Foods;");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // here you write the test. You can add a user and food as necessary to check your functionality
//}
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
}

