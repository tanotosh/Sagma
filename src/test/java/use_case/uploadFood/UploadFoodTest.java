package use_case.uploadFood;

import entity.User;
import entity.Food;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UploadFoodTest {

    private User gaia;

    @BeforeEach
    void setUp() {
        // Hard-coded user for testing
        gaia = new User(1, "gaia", "gaia@email.com", "password", 5.0f, 5, List.of("vegetarian"), null);
    }

    @Test
    void uploadFoodTest() {
        // gaia uploads a food item
        gaia.uploadFood("pizza", 5, "cheese, tomato sauce, and dough", Arrays.asList("vegan"), "/home/gaia/Photos/LunarNewYearPhotos/DSC_0563.JPG", "italian");

        // verify if food was uploaded correctly
        Food uploadedFood = gaia.getCurrentFood();
        assertNotNull(uploadedFood);
        assertEquals("pizza", uploadedFood.getName());
        assertEquals(5, uploadedFood.getQuantity());
        assertEquals("cheese, tomato sauce, and dough", uploadedFood.getIngredients());
        assertEquals(Arrays.asList("vegan"), uploadedFood.getDietaryRestrictions());
        assertEquals("/home/gaia/Photos/LunarNewYearPhotos/DSC_0563.JPG", uploadedFood.getImagePath());
        assertEquals("italian", uploadedFood.getCategory());

    }
}
