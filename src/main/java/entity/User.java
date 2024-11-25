package entity;

import java.io.File;
import java.util.*;

/**
 * A simple implementation of the User interface.
 */
public class User {
    private final int userID;
    private final String name;
    private final String email;
    private final String password;
    private Food currentFood;
    private float rating; // the average of all the ratings of all the foods in foodUploads AND currentFood (1). Has to be updated every time a new rating is updated, or gets re calculated every time it’s displayed. Probably better to update it each time something gets uploaded
    private int ratingsCount; // number of ratings in current rating tally
    private String dietaryRestrictions;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.rating = 0;
        this.ratingsCount = 0;
        this.dietaryRestrictions = null;
        this.userID = -1;
        this.email = "";

    }

    public User(int userID, String name, String email, String password, float rating, int ratingsCount, String dietaryRestrictions, Food food) {

        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.ratingsCount = ratingsCount;
        this.dietaryRestrictions = dietaryRestrictions;
        this.currentFood = food;

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

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setCurrentFood(entity.Food currentFood) {
        this.currentFood = currentFood;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}
