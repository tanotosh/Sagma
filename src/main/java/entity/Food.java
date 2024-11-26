package entity;

// import java.io.File;
import java.util.*;

public class Food {
    private User owner;
    private int foodID;
    private String name;
    private int quantity;
    private String ingredients;
    private List<String> dietaryRestrictions;
    // private File image;
    private List<User> swipedYes;
    private List<User> swipedNo;
    private String category;
    private float rating;
    private int ratingsCount;

    // at the time of food upload
    public Food(String name, int quantity, String ingredients, List<String> dietaryRestrictions, String category,
                User owner) {
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.category = category;
        this.owner = owner;
        this.swipedYes = new ArrayList<>();
        this.swipedNo = new ArrayList<>();
    }

    // at the time of data load from database
    public Food(int foodID, String name, int quantity, String ingredients, List<String> dietaryRestrictions,
                String category, User owner, List<User> swipedYes, List<User> swipedNo) {
        this.foodID = foodID;
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.category = category;
        this.owner = owner;
        this.swipedYes = swipedYes;
        this.swipedNo = swipedNo;
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

    /* public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    } */

    public List<User> getSwipedYes() { return swipedYes; }

    public void setSwipedYes(List<User> swipedYes) { this.swipedYes = swipedYes; }

    public List<User> getSwipedNo() { return swipedNo; }

    public void setSwipedNo(List<User> swipedNo) { this.swipedNo = swipedNo; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }

    public int getRatingsCount() { return ratingsCount; }

    public void setRatingsCount(int ratingsCount) { this.ratingsCount = ratingsCount; }

    public int getFoodID() { return foodID; }


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

    }

    // call from getMatches function...
    public void reduceQuantity() {
        // setQuantity... this.quantity -= 1;
    }

}


