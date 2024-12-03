package use_case.Rating;

import data_access.UserDAO;
import data_access.FoodDAO;
import entity.User;
import entity.Food;
import interface_adapter.state.LoginSessionState;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class AddRatingTest {

    private static final String DB_URL = "jdbc:sqlite:test.db";

    // this will be the code that all test cases need to copy to reset the test database to the original 5
    @BeforeEach

    // here you write the test. You can add a user and food as necessary to check your functionality
}
