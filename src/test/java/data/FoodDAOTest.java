package data;

import data_access.DatabaseDAO;
import data_access.FoodDAO;
import entity.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodDAOTest {
    private FoodDAO foodDAO;

    @BeforeEach
    void setUp() {
        DatabaseDAO databaseDAO = new DatabaseDAO(false); // Using in-memory data
        foodDAO = new FoodDAO(databaseDAO);
        databaseDAO.loadData();
    }

    @Test
    void testGetFoodById() {
        Food food = foodDAO.getFoodById(1);
        assertNotNull(food, "Food with ID 1 should exist.");
        assertEquals("Vegan Salad", food.getName(), "Food name should match.");
    }

    @Test
    void testAddFood() {
        Food newFood = new Food("Test Food", null, 1, "ingredient", List.of(),
                null, "Category");
        newFood.setFoodID(6);
        foodDAO.addFood(newFood);
        assertNotNull(foodDAO.getFoodById(6), "New food should be added successfully.");
    }
}


