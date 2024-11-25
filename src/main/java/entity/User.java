package entity;

import java.io.File;
import java.util.*;

/**
 * A simple implementation of the User interface.
 */
public class User {
    private final String name;
    private final String password;
    private Food currentFood;
    private List<Food> foodUploads; // past foods which there are no more servings of
    private float rating; // the average of all the ratings of all the foods in foodUploads AND currentFood (1). Has to be updated every time a new rating is updated, or gets re calculated every time it’s displayed. Probably better to update it each time something gets uploaded
    private int ratingsCount; // number of ratings in current rating tally
    private String dietaryRestrictions;
    private int userID;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.currentFood = null;
        this.foodUploads = new ArrayList<Food>();
        this.rating = 0;
        this.ratingsCount = 0;
        this.dietaryRestrictions = null;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void uploadFood(String name, int quantity, String ingredients, String dietaryRestrictions,
                           File image, String category){
        this.currentFood = new Food(name, quantity, ingredients, dietaryRestrictions, image, category);
    }

    public void swipeRight(Food food) {
        food.addSwipedYes(this);
    }

    public void swipeLeft(Food food) {
        food.addSwipedNo(this);
    }

    public Food getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(Food currentFood) {
        this.currentFood = currentFood;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public List<Food> getFoodUploads() {
        return foodUploads;
    }

    public void setFoodUploads(List<Food> foodUploads) {
        this.foodUploads = foodUploads;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }
}
