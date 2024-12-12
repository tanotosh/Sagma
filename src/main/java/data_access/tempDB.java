package data_access;

import entity.Food;
import entity.User;

import java.util.*;

public class tempDB {
    private final List<User> USERS;
    private final List<Food> FOODS;

    public tempDB(){
        User u1 = new User(1, "Alice", "alice@example.com", "password123", 4.5f, 10,
                List.of("vegan"), null);

        User u2 = new User(2, "Bob", "bob@example.com", "securepass", 3.8f, 5,
                List.of("gluten-free"), null);

        User u3 = new User(3, "Charlie", "charlie@example.com", "charliepass", 5.0f, 20,
                List.of("vegetarian"), null);

        User u4 = new User(4, "Diana", "diana@example.com", "diana123", 4.2f, 8,
                List.of("none"), null);

        User u5 = new User(5, "Eve", "eve@example.com", "eve123", 4.7f, 12,
                List.of("halal"), null);

        USERS = List.of(u1, u2, u3, u4, u5);

        Food f1 = new Food("Vegan Salad", u1, 5, "lettuce,tomato,cucumber",
                List.of("vegan"), null, "Western");
        f1.setFoodID(1);
        f1.setSwipedYes(List.of(u2, u5));
        f1.setSwipedNo(List.of(u3));

        Food f2 = new Food("Gluten-Free Pizza", u2, 3, "gluten-free dough,cheese,tomato sauce",
                List.of("gluten-free"), null, "Italian");
        f2.setFoodID(2);
        f2.setSwipedYes(List.of(u1));
        f2.setSwipedNo(List.of(u4));

        Food f3 = new Food("Vegetarian Sushi", u3, 10, "rice,seaweed,avocado",
                List.of("vegetarian"), null, "Japanese");
        f3.setFoodID(3);
        f3.setSwipedYes(List.of(u1, u2, u5));
        f3.setSwipedNo(List.of());

        Food f4 = new Food("Grilled Chicken", u5, 15, "Chicken, Spices, Olive Oil",
                List.of("halal"), null, "Mediterranean");
        f4.setFoodID(4);
        f4.setSwipedYes(List.of(u3));
        f4.setSwipedNo(List.of(u1));

        Food f5 = new Food("Pasta Salad", u4, 12, "Pasta, Veggies, Dressing",
                List.of("none"), null, "Fusion");
        f5.setFoodID(5);
        f5.setSwipedYes(List.of(u1, u4));
        f5.setSwipedNo(List.of());

        FOODS = List.of(f1, f2, f3, f4, f5);
    }

    public List<User> getUSERS() { return USERS; }

    public List<Food> getFOODS() { return FOODS; }
}
