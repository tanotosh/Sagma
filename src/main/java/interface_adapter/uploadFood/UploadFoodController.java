package interface_adapter.uploadFood;

import entity.User;
import use_case.uploadFood.UploadFoodInputBoundary;
import use_case.uploadFood.UploadFoodInputData;
import use_case.uploadFood.UploadFoodOutputBoundary;

import java.util.List;

public class UploadFoodController {

    private final UploadFoodInputBoundary uploadFoodUseCaseInteractor;

    public UploadFoodController(UploadFoodInputBoundary uploadFoodUseCaseInteractor) {
        this.uploadFoodUseCaseInteractor = uploadFoodUseCaseInteractor;
    }

    /**
     * Executes the Upload Food Use Case.
     * @param owner User uploading the food.
     * @param name Name of the food.
     * @param quantity Quantity of the food.
     * @param ingredients Ingredients of the food.
     * @param dietaryRestrictions Dietary restrictions.
     * @param imagePath Path to the food image.
     * @param category Category of the food.
     */
    public void execute(User owner, String name, int quantity, String ingredients, List<String> dietaryRestrictions, String imagePath, String category) {
        // Prepare the input data for the interactor
        final UploadFoodInputData uploadFoodInputData = new UploadFoodInputData(owner, name, quantity, ingredients, dietaryRestrictions, imagePath, category);

        // Call the interactor to execute the use case
        uploadFoodUseCaseInteractor.execute(uploadFoodInputData);

    }

}
