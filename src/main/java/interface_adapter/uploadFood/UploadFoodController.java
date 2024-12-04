package interface_adapter.uploadFood;

import entity.User;
import use_case.uploadFood.UploadFoodInputBoundary;
import use_case.uploadFood.UploadFoodInputData;

import java.util.List;

public class UploadFoodController {

    private final UploadFoodInputBoundary uploadFoodUseCaseInteractor;

    public UploadFoodController(UploadFoodInputBoundary uploadFoodUseCaseInteractor) {
        this.uploadFoodUseCaseInteractor = uploadFoodUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * the params
     */
    public void execute(User owner, String name, int quantity, String ingredients, List<String> dietaryRestrictions, String imagePath, String category) {
        final UploadFoodInputData uploadFoodInputData = new UploadFoodInputData(owner, name, quantity, ingredients, dietaryRestrictions, imagePath, category);

        uploadFoodUseCaseInteractor.execute(uploadFoodInputData);
    }

    public void switchToHomeView() {uploadFoodUseCaseInteractor.switchToHomeView();}
}
