package entity;

import java.io.File;

public class Food {
    private String name;
    private int quantity;
    private String[] ingredients;
    private String[] dietaryRestrictions;
    private File image;
    private String cuisine;

    public Food(String name, int quantity, String[] ingredients, String[] dietaryRestrictions, File image, String cuisine) {
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.image = image;
        this.cuisine = cuisine;
    }

}