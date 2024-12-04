package use_case.rating;

import entity.Food;

/**
 * The Input Data for the Rating Use Case.
 */
public class RatingInputData {

    private final Food food; // food getting rated
    private final int rating; // rating left

    public RatingInputData(Food food, int rating) {
        this.food = food;
        this.rating = rating;
    }
    public Food getFood() {
        return food;
    }
    public int getRating() {
        return rating;
    }
}
