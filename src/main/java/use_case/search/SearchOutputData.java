package use_case.search;

import entity.Food;
import use_case.login.LoginOutputData;
import use_case.uploadFood.UploadFoodInputData;

import java.util.List;

/**
 * Output Boundary for actions which are related searching.
 */
public class SearchOutputData {
    private final List<Food> filteredFoods;


    public SearchOutputData(List<Food> filteredFoods) {
        this.filteredFoods = filteredFoods;
    }
    public List<Food> getFilteredFoods() {
        return filteredFoods;
    }
}
