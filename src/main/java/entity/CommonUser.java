package entity;

import java.util.*;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private Food currentFood;
    private List<Food> foodUploads; // past foods which there are no more servings of
    private float rating; // the average of all the ratings of all the foods in foodUploads AND currentFood (1). Has to be updated every time a new rating is updated, or gets re calculated every time it’s displayed. Probably better to update it each time something gets uploaded
    private int ratingsCount; // number of ratings in current rating tally
    private List<String> dietaryRestrictions;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.currentFood = null;
        this.foodUploads = new ArrayList<>();
        this.rating = 0;
        this.ratingsCount = 0;
        this.dietaryRestrictions = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void uploadFood(String name, int quantity, List<String> ingredients, List<String> dietaryRestrictions,
                           File image){
        this.currentFood = new Food(name, quantity, ingredients, dietaryRestrictions, image);
    }

    public void swipeRight(Food food) {
        food.addSwipedYes(this);
        // i didnt know what to send
    }

    public void swipeLeft(Food food) {
        food.addSwipedNo(this);
    }

}
