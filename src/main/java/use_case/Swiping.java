package use_case;

import entity.Food;
import entity.User;
import api.GmailAPI;

public class Swiping {
    public User user;
    public Food currentfood;

    public Swiping(User user, Food currentfood) {
        this.user = user;
        this.currentfood = currentfood;
    }

    public void swipeRight() {
        user.swipeRight(currentfood);
    }

    public void swipeLeft() {
        user.swipeLeft(currentfood);
    }

    public boolean checkMatch() {
        return user.isMatch(currentfood.getOwner());
    }

    public void matchMade() throws Exception{

        try {
            // Code that throws Exception
            Food user1food = user.getCurrentFood();
            Food user2food = currentfood;
            User user1 = user;
            User user2 = currentfood.getOwner();

            user1food.setQuantity(user1food.getQuantity() - 1);
            user2food.setQuantity(user2food.getQuantity() - 1);

            if (user1food.getQuantity() == 0) {
                user1.setCurrentFood(null);
            }

            if (user2food.getQuantity() == 0) {
                user2.setCurrentFood(null);
            }

            GmailAPI mailer = new GmailAPI(user);
            mailer.sendMail("Exciting News!!", "You've got a match! Log on to see!!");
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
        }



    }

    public boolean checkFood() {
        if (currentfood.getQuantity() == 0){
            return false;
        }

        for (String userRestriction : user.getDietaryRestrictions()) {
            if (!currentfood.getDietaryRestrictions().contains(userRestriction)) {
                return false;
            }
        }
        return true;
    }
}