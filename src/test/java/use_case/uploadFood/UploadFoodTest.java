package use_case.uploadFood;

import entity.User;
import entity.Food;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UploadFoodTest {

    private UploadFoodInteractor interactor;
    private UploadFoodOutputBoundary mockPresenter;
    private User gaia;

    @BeforeEach
    void setUp() {
        // Hard-coded user for testing
        gaia = new User(1, "gaia", "gaia@email.com", "password", 5.0f, 5, List.of("vegetarian"), null);

        // Mock presenter to avoid UI interaction
        mockPresenter = new UploadFoodOutputBoundary() {

            @Override
            public void switchToHomeView() {
                // This method does nothing for testing, but it's called to verify switching behavior
            }
        };

        // Initialize the interactor with the mock presenter
        interactor = new UploadFoodInteractor(mockPresenter);
    }

    @Test
    void uploadFoodTest() {
        // Prepare input data for uploading food
        String name = "pizza";
        int quantity = 5;
        String ingredients = "cheese, tomato sauce, and dough";
        List<String> dietaryRestrictions = Arrays.asList("vegan");
        String imagePath = "/home/gaia/Photos/LunarNewYearPhotos/DSC_0563.JPG";
        String category = "italian";

        // Execute the interactor with the input data
        UploadFoodInputData inputData = new UploadFoodInputData(gaia, name, quantity, ingredients, dietaryRestrictions, imagePath, category);
        interactor.execute(inputData);

        // Verify the food was correctly uploaded and assigned to the user
        Food uploadedFood = gaia.getCurrentFood(); // Assuming the User class has a method to retrieve the food
        assertNotNull(uploadedFood);
        assertEquals(name, uploadedFood.getName());
        assertEquals(quantity, uploadedFood.getQuantity());
        assertEquals(ingredients, uploadedFood.getIngredients());
        assertEquals(dietaryRestrictions, uploadedFood.getDietaryRestrictions());
        assertEquals(imagePath, uploadedFood.getImagePath());
        assertEquals(category, uploadedFood.getCategory());
    }
}
