package interface_adapter.state;

import entity.Food;

import java.util.ArrayList;
import java.util.List;

public class SearchSessionState {
    private static final SearchSessionState instance = new SearchSessionState();
    private List<Food> foods;

    public List<Food> getFoods() {
        if (foods == null)
            return new ArrayList<>();
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    // singleton access
    public static SearchSessionState getInstance() { return instance; }
}
