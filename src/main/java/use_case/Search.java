package use_case;

import entity.Food;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Search {
    private static HashMap<String, List<Food>> cuisineToFood;

    public Search(HashMap<String, List<Food>> cuisineToFood) {
        this.cuisineToFood = cuisineToFood;
    }

    public static List<String> getCuisines(){
        Set<String> setCuisines = cuisineToFood.keySet();
        return new ArrayList<>(setCuisines);
    }

    public List<Food> getFilteredFoods(User user, String cuisine){
        List<Food> filteredFoods = new ArrayList<Food>();
        for (Food food :cuisineToFood.get(cuisine)){
            for (String restriction: user.getDietaryRestrictions()){
                if (food.getDietaryRestrictions().contains(restriction)){
                    filteredFoods.add(food);
                }
            }
        }
        return filteredFoods;
    }
}
