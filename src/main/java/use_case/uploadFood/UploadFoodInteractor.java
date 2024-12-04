package use_case.uploadFood;

import data_access.DataManager;
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

    public UploadFoodInteractor(UploadFoodOutputBoundary uploadFoodOutputBoundary) {
        this.uploadFoodPresenter = uploadFoodOutputBoundary;
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

        Food food = new Food(name, owner, quantity, ingredients, dietaryRestrictions, imagePath, category);
        FoodDAO.addFood(food);
        owner.setCurrentFood(food);
        UserDAO.updateUser(owner);

    }

    @Override
    public void switchToHomeView() {
        uploadFoodPresenter.switchToHomeView();
    }
}
