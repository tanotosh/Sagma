package use_case.swiping;

import entity.User;
import entity.Food;
import org.junit.jupiter.api.*;
import java.util.*;

import use_case.Swiping;

import static org.junit.jupiter.api.Assertions.*;

public class SwipingTest {

    private User jiya;
    private User gaia;
    private Food pizza;
    private Food pasta;

    private Swiping swiping;

    @BeforeEach
    void setUp() {
        // Create hard-coded users
        jiya = new User(1, "jiya", "jiya@example.com", "password", 4.5f, 5, List.of("vegetarian"), null);
        gaia = new User(2, "gaia", "gaia@example.com", "password", 4.2f, 10, List.of("vegan"), null);

        // Create hard-coded foods
        pasta = new Food("pasta", gaia, 3, "cheese", List.of("vegetarian"), null, "italian");
        pasta.setFoodID(1);
        pizza = new Food("pizza", jiya, 2, "tomato,cheese", List.of("vegetarian"), null, "italian");
        pasta.setFoodID(2);

        // Reset swiping object for each test
        swiping = new Swiping(jiya, pasta);
    }

    @Test
    void swipeLeftTest() {
        swiping.swipeLeft();
        assertTrue(pasta.getSwipedNo().contains(jiya), "Jiya should be in the swipedNo list of Pasta");
    }

//    @Test
//    void swipeLeftTest() {
//        User jiya = UserDAO.getUserById(1);
//        Food pasta = FoodDAO.getFoodById(2);
//
//        Swiping swiping = new Swiping(jiya, pasta);
//        swiping.swipeLeft();
//
//        // Assert that no match occurred
//        assertFalse(swiping.checkMatch());
//        assertTrue(pasta.getSwipedNo().contains(jiya));
//    }


    @Test
    void swipeRightTest() {
        swiping.swipeRight();
        assertTrue(pasta.getSwipedYes().contains(jiya), "Jiya should be in the swipedYes list of Pasta");
    }

    @Test
    void swipeRightMatchTest() {
        swiping.swipeRight();
        Swiping gaiaSwiping = new Swiping(gaia, pizza);
        gaiaSwiping.swipeRight();

        assertTrue(swiping.checkMatch(), "There should be a match for Jiya and Pasta");
        assertTrue(gaiaSwiping.checkMatch(), "There should be a match for Gaia and Pizza");
    }

//    @Test
//    void swipeRightWithMatchTest() throws Exception {
//        User jiya = UserDAO.getUserById(1);
//        User gaia = UserDAO.getUserById(2);
//        Food pasta = FoodDAO.getFoodById(2);
//        Food pizza = FoodDAO.getFoodById(1);
//
//        Swiping jiyaSwiping = new Swiping(jiya, pasta);
//        jiyaSwiping.swipeRight();
//
//        Swiping gaiaSwiping = new Swiping(gaia, pizza);
//        gaiaSwiping.swipeRight();
//
//        assertTrue(pasta.getSwipedYes().contains(jiya));
//        assertTrue(pizza.getSwipedYes().contains(gaia));
//
//        // Assert a match is made
//        assertTrue(jiyaSwiping.checkMatch());
//        assertTrue(gaiaSwiping.checkMatch());
//
//        // Test the matchMade functionality
//        jiyaSwiping.matchMade();
//
//        assertEquals(1, jiya.getCurrentFood().getQuantity());
//        assertEquals(2, pasta.getQuantity());
//        assertEquals(2, gaia.getCurrentFood().getQuantity());
//        assertEquals(1, pasta.getQuantity());
//    }

    @Test
    void swipeNoMatchTest() {
        swiping.swipeRight();
        Swiping gaiaSwiping = new Swiping(gaia, pizza);
        gaiaSwiping.swipeLeft();

        assertFalse(swiping.checkMatch(), "No match should be found for Jiya and Pasta");
        assertFalse(gaiaSwiping.checkMatch(), "No match should be found for Gaia and Pizza");
    }

//    @Test
//    void swipeRightWithoutMatchTest() {
//        User jiya = UserDAO.getUserById(1);
//        User gaia = UserDAO.getUserById(2);
//        Food pasta = FoodDAO.getFoodById(2);
//        Food pizza = FoodDAO.getFoodById(1);
//
//        Swiping jiyaSwiping = new Swiping(jiya, pasta);
//        jiyaSwiping.swipeRight();
//
//        Swiping gaiaSwiping = new Swiping(gaia, pizza);
//        gaiaSwiping.swipeLeft();
//
//        // Assert no match is made
//        assertFalse(jiyaSwiping.checkMatch());
//        assertFalse(gaiaSwiping.checkMatch());
//    }
//
//    @Test
//    void checkFoodFunctionTest() throws Exception {
//        User jiya = UserDAO.getUserById(1);
//        Food pasta = FoodDAO.getFoodById(2);
//
//        Swiping swiping = new Swiping(jiya, pasta);
//
//        assertFalse(swiping.checkFood());
//
//    }

}

