package data_access;

import entity.Food;
import entity.User;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;


public class UserDAO implements UserRepository {
    private final List<User> users;

    public UserDAO(DatabaseDAO DB) {
        this.users = DB.getUSERS();
    }

    @Override
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getUserID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

}