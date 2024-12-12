package use_case.uploadFood;

import data_access.FoodDAO;
import data_access.UserDAO;
import entity.Food;
import entity.User;

import java.util.List;

/**
 * The Upload Food Interactor.
 */
public class UploadFoodInteractor implements UploadFoodInputBoundary{
    private final UploadFoodOutputBoundary uploadFoodPresenter;

    public UploadFoodInteractor(UploadFoodOutputBoundary uploadFoodPresenter) {
        this.uploadFoodPresenter = uploadFoodPresenter;
    }


    @Override
    public void execute(UploadFoodInputData uploadFoodInputData) {
        User owner = uploadFoodInputData.getOwner();
        String name = uploadFoodInputData.getName();
        int quantity = uploadFoodInputData.getQuantity();
        String ingredients = uploadFoodInputData.getIngredients();
        List<String> dietaryRestrictions = uploadFoodInputData.getDietaryRestrictions();
        String imagePath = uploadFoodInputData.getImagePath();
        String category = uploadFoodInputData.getCategory();

        // Create food object
        Food food = new Food(name, owner, quantity, ingredients, dietaryRestrictions, imagePath, category);

        // Set the uploaded food to the User
        owner.setCurrentFood(food);

        uploadFoodPresenter.switchToHomeView();

    }

}
