package data_access;

import entity.User;

import java.util.List;

public interface UserRepository {

    User getUserById(int id);

    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    void addUser(User user);

}