package use_case;

import data_access.DataManager;
import entity.Food;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Search {
    private static HashMap<String, List<Food>> categoryToFood;

    public Search() {
        DataManager data = new DataManager();
        List<Food> listFoods= data.getFoods();
        for (Food food : listFoods) {
            String category = food.getCategory();
            if (categoryToFood.containsKey(category)) {
                categoryToFood.get(category).add(food);
            } else{
                List<Food> newFoodList = new ArrayList<>();
                newFoodList.add(food);
                categoryToFood.put(category, newFoodList);
            }
        }

    }

    public static List<String> getCategory(){
        Set<String> setCategories = categoryToFood.keySet();
        return new ArrayList<>(setCategories);
    }

    public static List<Food> getFilteredFoods(User user, String cuisine){
        List<Food> filteredFoods = new ArrayList<Food>();
        for (Food food :categoryToFood.get(cuisine)){
            for (String restriction: user.getDietaryRestrictions()){
                if (food.getDietaryRestrictions().contains(restriction)){
                    filteredFoods.add(food);
                }
            }
        }
        return filteredFoods;
    }
}
