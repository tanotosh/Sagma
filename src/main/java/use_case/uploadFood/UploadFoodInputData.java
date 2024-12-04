package use_case.uploadFood;

import entity.User;

import java.util.List;

/**
 * The Input Data for the Upload Food Use Case.
 */
public class UploadFoodInputData {

    private final User owner;
    private final String name;
    private final int quantity;
    private final String ingredients;
    private final List<String> dietaryRestrictions;
    private final String imagePath;
    private final String category;

    public UploadFoodInputData(User owner, String name, int quantity, String ingredients, List<String> dietaryRestrictions, String imagePath, String category) {
        this.owner = owner;
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.imagePath = imagePath;
        this.category = category;
    }
    public User getOwner() {
        return owner;
    }
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getIngredients() {
        return ingredients;
    }
    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
    public String getImagePath() {
        return imagePath;
    }
    public String getCategory() {
        return category;
    }
}
