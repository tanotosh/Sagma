package use_case.dietaryRestriction;

import data_access.FoodDAO;
import data_access.UserDAO;
import entity.Food;
import entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.DietaryRestriction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class dietaryRestrictionTest {

    private User jiya;
    private User gaia;

    @BeforeEach
    void setUp() {
        jiya = new User(1,"jiya", "jiya@email.com", "password", 5, 5, new ArrayList<>(), null);

        List<String> dietaryRestrictions = new ArrayList<>();
        dietaryRestrictions.add("vegan");
        dietaryRestrictions.add("halal");

        gaia  = new User(2,"gaia", "gaia@email.com", "passwordd", 5, 5, dietaryRestrictions, null);

    }

    @Test
    void addingRestrictionTest() {

        new DietaryRestriction(jiya, "vegan").addRestriction();
        new DietaryRestriction(jiya, "halal").addRestriction();

        List<String> expectedRestriction = new ArrayList<>();
        expectedRestriction.add("vegan");
        expectedRestriction.add("halal");

        assertEquals(jiya.getDietaryRestrictions(), expectedRestriction);

    }

    @Test
    void removingRestrictionTest() {
        List<String> dietaryRestrictions = new ArrayList<>();
        dietaryRestrictions.add("vegan");
        dietaryRestrictions.add("halal");

        User gaia  = new User(2,"gaia", "gaia@email.com", "passwordd", 5, 5, dietaryRestrictions, null);

        new DietaryRestriction(gaia, "vegan").removeRestriction();

        List<String> expectedRestriction = new ArrayList<>();
        expectedRestriction.add("halal");

        assertEquals(gaia.getDietaryRestrictions(), expectedRestriction);

    }
}
