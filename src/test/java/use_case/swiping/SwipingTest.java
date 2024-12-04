package use_case.swiping;

import data_access.UserDAO;
import data_access.FoodDAO;
import entity.User;
import entity.Food;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import use_case.Swiping;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SwipingTest {

    private static final String DB_URL = "jdbc:sqlite:test.db";

    @BeforeEach
    void setUp() throws Exception {
        // Populate database with data before each test
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {
            stmt.execute("INSERT into User (user_id, name, email, password, rating, ratings_count, " +
                    "dietary_restrictions, current_food_id) VALUES " +
                    "(1, 'jiya', 'jiya@email.com', 'password', 5, 5, 'vegetarian', 1), " +
                    "(2, 'gaia', 'gaia@email.com', 'passwordd', 5, 5, 'vegan', 2)");

            stmt.execute("INSERT into Foods (food_id, name, quantity, ingredients, dietary_restrictions, cuisine, " +
                    "owner_id, image_path) VALUES " +
                    "(1, 'pizza', 2, 'cheese, tomato sauce', 'vegetarian', 'italian', 1, NULL), " +
                    "(2, 'pasta', 3, 'cheese, tomato sauce', 'vegan', 'italian', 2, NULL)");

            stmt.execute("INSERT into Swipes (swipe_id, user_id, food_id, is_right_swipe) VALUES " +
                    "(1, 1, 2, 0), " +
                    "(2, 2, 1, 1)");
        }
    }

    @Test
    void swipeLeftTest() {
        User jiya = UserDAO.getUserById(1);
        Food pasta = FoodDAO.getFoodById(2);

        Swiping swiping = new Swiping(jiya, pasta);
        swiping.swipeLeft();

        // Assert that no match occurred
        assertFalse(swiping.checkMatch());
        assertTrue(pasta.getSwipedNo().contains(jiya));
    }

    @Test
    void swipeRightWithMatchTest() throws Exception {
        User jiya = UserDAO.getUserById(1);
        User gaia = UserDAO.getUserById(2);
        Food pasta = FoodDAO.getFoodById(2);
        Food pizza = FoodDAO.getFoodById(1);

        Swiping jiyaSwiping = new Swiping(jiya, pasta);
        jiyaSwiping.swipeRight();

        Swiping gaiaSwiping = new Swiping(gaia, pizza);
        gaiaSwiping.swipeRight();

        assertTrue(pasta.getSwipedYes().contains(jiya));
        assertTrue(pizza.getSwipedYes().contains(gaia));

        // Assert a match is made
        assertTrue(jiyaSwiping.checkMatch());
        assertTrue(gaiaSwiping.checkMatch());

        // Test the matchMade functionality
        jiyaSwiping.matchMade();

        assertEquals(1, jiya.getCurrentFood().getQuantity());
        assertEquals(2, pasta.getQuantity());
        assertEquals(2, gaia.getCurrentFood().getQuantity());
        assertEquals(1, pasta.getQuantity());
    }

    @Test
    void swipeRightWithoutMatchTest() {
        User jiya = UserDAO.getUserById(1);
        User gaia = UserDAO.getUserById(2);
        Food pasta = FoodDAO.getFoodById(2);
        Food pizza = FoodDAO.getFoodById(1);

        Swiping jiyaSwiping = new Swiping(jiya, pasta);
        jiyaSwiping.swipeRight();

        Swiping gaiaSwiping = new Swiping(gaia, pizza);
        gaiaSwiping.swipeLeft();

        // Assert no match is made
        assertFalse(jiyaSwiping.checkMatch());
        assertFalse(gaiaSwiping.checkMatch());
    }

    @Test
    void checkFoodFunctionTest() throws Exception {
        User jiya = UserDAO.getUserById(1);
        Food pasta = FoodDAO.getFoodById(2);

        Swiping swiping = new Swiping(jiya, pasta);

        assertFalse(swiping.checkFood());

    }

    @AfterAll
    static void tearDownAll() throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {

            // Clear all tables
            stmt.execute("DELETE FROM Users;");
            stmt.execute("DELETE FROM Foods;");
            stmt.execute("DELETE FROM Swipes;");
        }
    }
}

