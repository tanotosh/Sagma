package use_case;

import data_access.DataManager;
import entity.Food;
import entity.User;

import java.util.List;

public class Swiping {
    public User currentuser;
    public Food currentfood;

    public Swiping(User currentuser, Food currentfood) {
        this.currentuser = currentuser;
        this.currentfood = currentfood;
    }

    public void swipeRight() {
        currentuser.swipeRight(currentfood);
    }

    public void swipeLeft() {
        currentuser.swipeLeft(currentfood);
    }

    public boolean checkMatch() {
        return currentuser.isMatch(currentfood.getOwner());
    }

    public void matchMade() {
        Food user1food = currentuser.getCurrentFood();
        Food user2food = currentfood;
        User user1 = currentuser;
        User user2 = currentfood.getOwner();

        user1food.setQuantity(user1food.getQuantity() - 1);
        user2food.setQuantity(user2food.getQuantity() - 1);

        if (user1food.getQuantity() == 0) {
            user1.setCurrentFood(null);
        }

        if (user2food.getQuantity() == 0) {
            user2.setCurrentFood(null);
        }
    }

    public boolean checkFood() {
        for (String userRestriction : currentuser.getDietaryRestrictions()) {
            if (!currentfood.getDietaryRestrictions().contains(userRestriction)) {
                return false;
            }
        }
        return true;
    }
}