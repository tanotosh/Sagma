package entity;

import java.io.File;
import java.util.*;

public class Food {
    private CommonUser Owner;
    private String name;
    private int quantity;
    private List<String> ingredients; // wont users just input a string of ingredients, comma separated? are we converting that to a list?
    private List<String> dietaryRestrictions; // similar comment as ingrdients
    private File image;
    private List<CommonUser> swipedYes; //list of people that have swiped right on the food
    private List<CommonUser> swipedNo; //list of people that have swiped left on the food
    private String category; //INPUT: drop down menu
    private List<Integer> ratings; // adds to list as ratings get added

    public Food(String name, int quantity, List<String> ingredients, List<String> dietaryRestrictions, File image,
                String category) {
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.image = image;
        this.swipedYes = new ArrayList<>();
        this.swipedNo = new ArrayList<>();
        this.category = category;
        this.ratings = new ArrayList<>();
    }

    public void addSwipedYes(CommonUser user){
        this.swipedYes.add(user);
        // dataManager.handleSwipe(user, food, true);
    }

    public void addSwipedNo(CommonUser user){
        this.swipedNo.add(user);
        // dataManager.handleSwipe(user, food, false);
    }
    // i know that we are making these lists to do the match handling but it kinda breaks down when we try to generate
    // the feed that each user sees. it would be so tedious to go through every list of every food just to see who
    // swiped no on which food so we don't show them that food. maybe we go back to having swipedYes and No lists (as a
    // list of food) in the user class so everything's easier?

}