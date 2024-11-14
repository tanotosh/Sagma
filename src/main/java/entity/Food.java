package entity;

import java.io.File;

public class Food {
    private String name;
    private int quantity;
    private String[] ingredients;
    private String[] dietaryRestrictions;
    private File image;

    public Food(String name, int quantity, String[] ingredients, String[] dietaryRestrictions, File image) {
        this.name = name;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.dietaryRestrictions = dietaryRestrictions;
        this.image = image;
    }

}