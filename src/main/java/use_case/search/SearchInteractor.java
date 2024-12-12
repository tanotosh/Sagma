package use_case.search;

import entity.Food;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Search Interactor.
 */

public class SearchInteractor implements SearchInputBoundary {
    private final SearchOutputBoundary searchPresenter;

    static List<Food> mockdb; //cannot connect to the project's database

    public SearchInteractor(SearchOutputBoundary searchOutputBoundary) {
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData){
        User currentUser = searchInputData.getUser();
        HashMap<String, List<Food>> categoryToFood = new HashMap<String, List<Food>>();
        List<Food> listFoods = mockdb;
        for (Food food : listFoods) {
            String categories = food.getCategory();
            if (categoryToFood.containsKey(categories)) {
                categoryToFood.get(categories).add(food);
            } else {
                List<Food> newFoodList = new ArrayList<>();
                newFoodList.add(food);
                categoryToFood.put(categories, newFoodList);
            }
        }
        String category = searchInputData.getCategory();
        List<Food> filteredFoods = new ArrayList<>();
        for (Food food :categoryToFood.get(category)){
            for (String restriction: currentUser.getDietaryRestrictions()){
                if (food.getDietaryRestrictions().contains(restriction)){
                    filteredFoods.add(food);
                }
            }
        }
        // No failure case here due to stubbed data. In case, there is failure case.
        searchPresenter.prepareSuccessView(new SearchOutputData(filteredFoods));
    }
}
