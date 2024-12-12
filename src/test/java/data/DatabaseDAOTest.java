package data;

import data_access.DatabaseDAO;
import entity.User;
import entity.Food;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseDAOTest {
    private DatabaseDAO databaseDAO;

    @BeforeEach
    void setUp() {
        databaseDAO = new DatabaseDAO(false); // Using in-memory setup
    }

    @Test
    void testLoadData() {
        databaseDAO.loadData();
        List<User> users = databaseDAO.getUSERS();
        List<Food> foods = databaseDAO.getFOODS();

        assertNotNull(users, "Users list should not be null after loading data.");
        assertNotNull(foods, "Foods list should not be null after loading data.");
    }

    @Test
    void testSaveData() {
        assertDoesNotThrow(() -> databaseDAO.saveData(), "Saving data should not throw any exceptions.");
    }
}
