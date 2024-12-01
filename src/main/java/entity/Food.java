package entity;

// import java.io.File;
import data_access.FoodDAO;

import java.util.*;

public class Food {
    private User owner;
    private int foodID;
    private String name;
    private int quantity;
    private String ingredients;
    private List<String> dietaryRestrictions;
    private String imagePath;
    private List<User> swipedYes;
    private List<User> swipedNo;
    private String category;

    // at the time of food upload
    public Food(String name, User owner, int quantity, String ingredients, List<String> dietaryRestrictions,
                String imagePath, String category) {
        this.name = name;
        this.owner = owner;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions != null ? dietaryRestrictions : new ArrayList<>();
        this.category = category;
        this.imagePath = imagePath;

        // Default values for database fields
        this.foodID = 0; // Will be set when saved to the database
        this.swipedYes = new ArrayList<>();
        this.swipedNo = new ArrayList<>();

    }


    public void addSwipedYes(User user){
        this.swipedYes.add(user);
        // DataManager.handleSwipe(user, food, true);
    }

    public void addSwipedNo(User user){
        this.swipedNo.add(user);
        // DataManager.handleSwipe(user, food, false);
    }

    // might need to compute matches
    public boolean hasSwiped(User user) {
        return false;
    }

    public void addRating(int rating) {
        this.owner.updateRating(rating); //when food gets a rating, the owner's rating gets updated.
    }

    // call from getMatches function...
    public void reduceQuantity() {
        // setQuantity... this.quantity -= 1;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) { this.owner = owner; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public List<User> getSwipedYes() { return swipedYes; }

    public void setSwipedYes(List<User> swipedYes) { this.swipedYes = swipedYes; }

    public List<User> getSwipedNo() { return swipedNo; }

    public void setSwipedNo(List<User> swipedNo) { this.swipedNo = swipedNo; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getFoodID() { return foodID; }

    public void setFoodID(int foodID) { this.foodID = foodID; }

}


