package use_case.profile;

import data_access.UserDAO;
import data_access.FoodDAO;
import entity.User;
import entity.Food;
import interface_adapter.state.LoginSessionState;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class profileTest {

    private static final String DB_URL = "jdbc:sqlite:test.db";

    // this will be the code that all test cases need to copy to reset the test database to the original 5
    @BeforeEach
    void setUp() throws Exception {
        // populate database with data before each test
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement stmt = connection.createStatement()) {
            // can add less values like for rating the execute statement could look like this:
            // stmt.execute("INSERT into User (user_id, rating, ratings_count, current_food_id) VALUES (1,'jiya', 5," +
            //                    " 5, 1) ");
            // the statement will only populate the attributes / columns that are needed for testing specific use cases
            // this stays true for food and swipes table as well
            stmt.execute("INSERT into User (user_id, name, email, password, rating, ratings_count, " +
                    "dietary_restrictions, current_food_id) VALUES (1,'gaia', 'gaia@email.com', 'password', 5, 5, " +
                    "'vegetarian') ");
        }
    }

    @Test
    void profileTest() {
        User gaia = UserDAO.getUserById(1);
        gaia.uploadFood("pizza", 5, "cheese, tomato sauce, and dough", Arrays.asList("vegan"), "/home/gaia/Photos/LunarNewYearPhotos/DSC_0563.JPG", "italian");
        Food uploadedFood = gaia.getCurrentFood();
        assertNotNull(uploadedFood);
        assertEquals("pizza", uploadedFood.getName());

        // test matches


    }
}
