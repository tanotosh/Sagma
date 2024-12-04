package use_case.search;

import data_access.UserDAO;
import entity.User;
import entity.Food;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    private static final String DB_URL = "jdbc:sqlite:test.db";

    // this will be the code that all test cases need to copy to reset the test database to the original 5
    @BeforeEach
    void setUp() throws Exception {
        // populate database with data before each test
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {
            // can add less values like for rating the execute statement could look like this:
            // stmt.execute("INSERT into User (user_id, rating, ratings_count, current_food_id) VALUES (1,'jiya', 5," +
            //                    " 5, 1) ");
            // the statement will only populate the attributes / columns that are needed for testing specific use cases
            // this stays true for food and swipes table as well
            stmt.execute("INSERT into User (user_id, name, email, password, rating, ratings_count, " +
                    "dietary_restrictions, current_food_id) VALUES " +
                    "(1,'jiya', 'jiya@email.com', 'password', 5, 5, 'vegetarian', 1)," +
                    "(2,'gaia', 'gaia@email.com', 'passwordd', 5, 5,'vegan', 2)");

            stmt.execute("INSERT into Foods (food_id, name, quantity, ingredients, dietary_restrictions, cuisine, " +
                    "owner_id, image_path) VALUES " +
                    "(1, 'pizza', 2, 'cheese', 'vegetarian', 'italian', 1, NULL)," +
                    "(2, 'pasta', 3, 'cheese', 'vegan', 'italian', 2, NULL)," +
                    "(3, 'bimbimbap', 5, 'rice', 'halal', 'korean', 2, NULL)");

            stmt.execute("INSERT into Swipes (swipe_id, user_id, food_id, is_right_swipe) VALUES " +
                    "(1, 1, 2, 1), " +
                    "(2, 2, 1, 1)");
        }

    }

    // adding this so that at the end of all tests, the database is empty
    @AfterAll
    static void tearDownAll() throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {

            // Delete from parent tables; cascading will clean up child tables
            stmt.execute("DELETE FROM Users;");
            stmt.execute("DELETE FROM Foods;");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getCategoryTest() {
        List<String> categories = Search.getCategory();
        assertEquals(2, categories.size());
        List<String> expectedCategories = Arrays.asList("italian", "korean");
        assertEquals(expectedCategories, categories);

    }

    @Test
    void getFilteredFoodsTest() {
        User gaia = UserDAO.getUserById(1);
        List<Food> filteredFoods = Search.getFilteredFoods(gaia, "italian");
        assertEquals(1, filteredFoods.size());
        assertEquals("pasta", filteredFoods.get(0).getName());
    }


    // here you write the test. You can add a user and food as necessary to check your functionality
}