package data;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        DatabaseDAO databaseDAO = new DatabaseDAO(false); // Using in-memory data
        userDAO = new UserDAO(databaseDAO);
        databaseDAO.loadData();
    }

    @Test
    void testGetUserById() {
        User user = userDAO.getUserById(1);
        assertNotNull(user, "User with ID 1 should exist.");
        assertEquals("Alice", user.getName(), "User name should match.");
    }

    @Test
    void testGetUserByEmail() {
        User user = userDAO.getUserByEmail("alice@example.com");
        assertNotNull(user, "User with email alice@example.com should exist.");
        assertEquals("Alice", user.getName(), "User name should match.");
    }

    @Test
    void testExistsByEmail() {
        assertTrue(userDAO.existsByEmail("alice@example.com"), "Email alice@example.com should exist.");
        assertFalse(userDAO.existsByEmail("nonexistent@example.com"), "Non-existent email should return false.");
    }

    @Test
    void testAddUser() {
        User newUser = new User(6, "John Doe", "john@example.com", "password", 0,
                0, List.of(), null);
        userDAO.addUser(newUser);
        assertNotNull(userDAO.getUserByEmail("john@example.com"), "New user should be added successfully.");
    }
}

