package use_case.rating;

public class RatingOutputData {
    private final float rating;
    private final String ownerName;
    private final String foodName;

    public RatingOutputData(float rating, String ownerName, String foodName) {
        this.rating = rating;
        this.ownerName = ownerName;
        this.foodName = foodName;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public String getFoodName() {
        return foodName;
    }
    public float getRating() {
        return rating;
    }
}
