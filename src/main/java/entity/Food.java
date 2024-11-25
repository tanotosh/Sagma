package entity;

import java.io.File;
import java.util.*;

public class Food {
    private User Owner;
    private String name;
    private int quantity;
    private String ingredients; // wont users just input a string of ingredients, comma separated? are we converting that to a list?
    private String dietaryRestrictions; // similar comment as ingrdients
    private File image;
    private List<User> swipedYes; //list of people that have swiped right on the food
    private List<User> swipedNo; //list of people that have swiped left on the food
    private String category; //INPUT: drop down menu
    private float rating; // the average of all the ratings that have been submitted for this specific food
    private int ratingsCount; // the number of ratings that have been submitted so far for this food

    public Food(String name, int quantity, String ingredients, String dietaryRestrictions, File image,
                String category) {
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.image = image;
        this.swipedYes = new ArrayList<>();
        this.swipedNo = new ArrayList<>();
        this.category = category;
        this.rating = 0;
        this.ratingsCount = 0;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User Owner) {
        this.Owner = Owner;
    }

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

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public List<User> getSwipedYes() {
        return swipedYes;
    }

    public void setSwipedYes(List<User> swipedYes) {
        this.swipedYes = swipedYes;
    }

    public List<User> getSwipedNo() {
        return swipedNo;
    }

    public void setSwipedNo(List<User> swipedNo) {
        this.swipedNo = swipedNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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





    public void addSwipedYes(User user){
        this.swipedYes.add(user);
        // dataManager.handleSwipe(user, food, true);
    }

    public void addSwipedNo(User user){
        this.swipedNo.add(user);
        // dataManager.handleSwipe(user, food, false);
    }
    // i know that we are making these lists to do the match handling but it kinda breaks down when we try to generate
    // the feed that each user sees. it would be so tedious to go through every list of every food just to see who
    // swiped no on which food so we don't show them that food. maybe we go back to having swipedYes and No lists (as a
    // list of food) in the user class so everything's easier?

}


