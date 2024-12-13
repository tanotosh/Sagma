package data;

import data_access.DatabaseDAO;
import data_access.FoodDAO;
import data_access.SwipeDAO;
import entity.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwipeDAOTest {
    private SwipeDAO swipeDAO;

    @BeforeEach
    void setUp() {
        DatabaseDAO databaseDAO = new DatabaseDAO(false); // Using in-memory data
        FoodDAO foodDAO = new FoodDAO(databaseDAO);
        swipeDAO = new SwipeDAO(foodDAO);
        databaseDAO.loadData();
    }

    @Test
    void testAddSwipeYes() {
        swipeDAO.addSwipeYes(1, 2); // Adding Bob to swipeYes for Food ID 1
        List<entity.User> swipedYes = swipeDAO.getSwipedYes(1);
        assertTrue(swipedYes.stream().anyMatch(user -> user.getUserID() == 2),
                "User 2 should be in swipeYes for Food 1.");
    }

    @Test
    void testAddSwipeNo() {
        swipeDAO.addSwipeNo(1, 3); // Adding Charlie to swipeNo for Food ID 1
        List<entity.User> swipedNo = swipeDAO.getSwipedNo(1);
        assertTrue(swipedNo.stream().anyMatch(user -> user.getUserID() == 3),
                "User 3 should be in swipeNo for Food 1.");
    }

    @Test
    void testGetSwipedYes() {
        List<entity.User> swipedYes = swipeDAO.getSwipedYes(3); // Vegetarian Sushi
        assertFalse(swipedYes.isEmpty(), "There should be users who swipedYes on Food 3.");
    }

    @Test
    void testGetSwipedNo() {
        List<entity.User> swipedNo = swipeDAO.getSwipedNo(4); // Grilled Chicken
        assertFalse(swipedNo.isEmpty(), "There should be users who swipedNo on Food 4.");
    }
}

