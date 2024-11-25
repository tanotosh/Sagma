package entity;

/**
 * The representation of a user in our program. We combined Common User and User into this.
 */
public class User {

    private final String name;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
