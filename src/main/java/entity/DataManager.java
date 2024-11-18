package entity;

public class DataManager {
    // attributes

    // constructor

    // addUser, addFood, getFeed (depending on who the user is, list of food user hasnt swiped on),
    // filterByDietaryRestrictions (refine the feed, can incorporate categories)

    public void handleSwipe(CommonUser user, Food food, boolean isRightSwipe){
        if (isRightSwipe) {
            // check if the other user has also swiped right on this user, have to figure out the logic on how we are
            // saving that data
            // if another user has swiped right on this one, call handleMatch(user1, user2, food1, food2)
            // use getters to get food from each user
        }
    }

    // public void handleMatch(CommonUser user1, CommonUser user2, Food food1, Food food2) { }

    // also need to figure out how to right data into the json file (pom.xm) - saveDataToJson(String filepath)
    // take data from the json file and populate the java objects as well (wrappers)
    // second step will be part of a load data function - loadDataFromJson(String filepath)
}
