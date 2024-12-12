package use_case.Rating;

import entity.Food;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.rating.RatingController;
import interface_adapter.rating.RatingPresenter;
import interface_adapter.rating.RatingViewModel;
import org.junit.jupiter.api.BeforeEach;
import use_case.rating.RatingInteractor;
import view.HomeView;
import view.RatingView;
import view.SearchPageView;
import view.UploadFoodView;

import javax.swing.*;
import java.util.Arrays;

public class RatingViewTest {
    public static void main(String[] args) {
        // Set the Look and Feel to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeView = new HomeViewModel();

        // Create a test User object
        User gaia = new User(2, "Gaia", "gaia@email.com", "password", 5.0f, 5, Arrays.asList("vegan"), null);

        // Create a test Food object
        Food testFood = new Food("pancakes", gaia, 5, "butter, milk, eggs, flour", Arrays.asList("vegetarian"),
                "/home/gaia/IdeaProjects/Sagma/src/main/resources/images/PancakePicture.jpg", "italian");

        // Create the necessary layers
        RatingViewModel ratingViewModel = new RatingViewModel();
        RatingPresenter ratingPresenter = new RatingPresenter(viewManagerModel, homeView);
        RatingInteractor ratingInteractor = new RatingInteractor(ratingPresenter);

        // Controller
        RatingController ratingController = new RatingController(ratingInteractor, null); // Temporary null for the view

        // View
        RatingView ratingView = new RatingView(testFood, ratingController);

        // Link controller and view
        ratingController = new RatingController(ratingInteractor, ratingView);


        // Set up the JFrame for visual testing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rating View Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.add(ratingView);
            frame.setVisible(true);
        });

        System.out.println("Please interact with the Rating View and verify functionality.");
    }
}
