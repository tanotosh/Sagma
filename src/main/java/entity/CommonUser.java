package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.food = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void uploadFood(String name, int quantity, String[] ingredients, String[] dietaryRestrictions, File image, String cuisine){
        this.food = new Food(name, quantity, ingredients, dietaryRestrictions, image, cuisine);
    }
}
