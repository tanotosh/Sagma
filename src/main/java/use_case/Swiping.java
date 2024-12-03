package use_case;

import entity.Food;
import entity.User;
import api.GmailAPI;

public class Swiping {
    private final User currentuser;
    private final Food currentfood;

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

    public void matchMade() throws Exception{

        try {
            // Code that throws Exception
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

            GmailAPI mailer = new GmailAPI();
            mailer.sendMail("Exciting News!!", "You've got a match! Log on to see!!");
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception
        }



    }

    public boolean checkFood() {
        if (currentfood.getQuantity() == 0){
            return false;
        }

        for (String userRestriction : currentuser.getDietaryRestrictions()) {
            if (!currentfood.getDietaryRestrictions().contains(userRestriction)) {
                return false;
            }
        }
        return true;
    }
}