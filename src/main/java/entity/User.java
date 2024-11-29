package entity;

import data_access.UserDAO;

import java.io.File;
import java.util.*;

public class User {
    private final int userID;
    private final String name;
    private final String email;
    private final String password;
    private Food currentFood;
    private float rating;
    private int ratingsCount;
    private List<String> dietaryRestrictions;

    /*public User(String name, String password) {
        this.name = name;
        this.email = "";
        this.password = password;
        this.rating = 0;
        this.ratingsCount = 0;
        this.dietaryRestrictions = null;
        this.userID = -1;
    }*/

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = 0;
        this.ratingsCount = 0;
        this.dietaryRestrictions = null;
        this.userID = -1;
    }

    public User(int userID, String name, String email, String password, float rating, int ratingsCount,
                List<String> dietaryRestrictions, Food food) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.ratingsCount = ratingsCount;
        this.dietaryRestrictions = dietaryRestrictions;
        this.currentFood = food;
    }


    public void uploadFood(String name, int quantity, String ingredients, List<String> dietaryRestrictions,
                           String category) {
        this.currentFood = new Food(name, quantity, ingredients, dietaryRestrictions, category, this);
    }

    public void swipeRight(Food food) {
        food.addSwipedYes(this);
    }

    public void swipeLeft(Food food) {
        food.addSwipedNo(this);
    }

    /*
    Leaves a rating on someone else's food. Uses the food function addRating to modify the food's rating. Takes the food
    to leave the rating for and the rating (1, 2, 3, 4, or 5)
     */
    public void rateFood(Food food, int rating) {
        food.addRating(rating);
    }

    /*
    changes user's rating. called every time the user's foods get a new rating.
     */
    public void updateRating(int rating) {
        float count = this.rating * this.ratingsCount;
        count = count + rating;
        this.ratingsCount = this.ratingsCount + 1;
        this.rating = count/this.ratingsCount;
        UserDAO.updateUser(this);
    }

    public List<Food> getMatches() {
        return null;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public Food getCurrentFood() {
        return currentFood;
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

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

}
