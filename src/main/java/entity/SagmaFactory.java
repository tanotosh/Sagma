package entity;

public class SagmaFactory {
    //this Factory will be used to generate all our entities. We combined User Factory and Common user Factory into this.
    public User create(String name, String password) {
        return new User(name, password);
    }
}
