package usecase;

import java.util.HashMap;

/**
 * The Search function
 */

public class Search {
    private HashMap<String,List<String>> cuisineToFood; //maps cuisine to food

    public Search(HashMap<String,String> cuisineToFood) {
        this.cuisineToFood = cuisineToFood; //not correct, need to figure out how to read database to compile all foods
    }

    public Set<String> getcuisines() {
        return cuisineToFood.keySet();
    }

    public List<Food> getfilteredfoods(String cuisine){ //takes from choice in search page
        List<Food> filteredFoods = new List<Food>;
        filtered_cuisine = new List<Food>;
        for (String food : cuisineToFood.get(cuisine) { // need to add private instances dietary restrictionsin CommonUser
            for (String restriction: CommonUser.dietaryRestrictions){
                if restriction in food.dietaryRestrictions{
                    filteredFoods.add(food);
                }
            }
        }
    }
}