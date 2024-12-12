package use_case.Rating;
import entity.Food;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.rating.RatingController;
import interface_adapter.rating.RatingPresenter;
import interface_adapter.rating.RatingViewModel;
import use_case.rating.RatingInputBoundary;
import use_case.rating.RatingInteractor;
import use_case.rating.RatingOutputBoundary;
import view.RatingView;

import javax.swing.*;
import java.util.Arrays;

public class RatingViewTest {
    public static void main(String[] args) {
        // Set the Look and Feel to the system default (helps with UI rendering issues)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a User object and Food object for testing
        User testUser = new User("testUser", "testEmail@gmail.com", "password123");
        Food testFood = new Food("poutine", testUser, 5, "fries, gravy", Arrays.asList("dietary restrictions"), "/home/gaia/University of Toronto/Courses/CSC 207/PoutinePicture.jpg", "category");

        // Create the RatingView instance
        RatingView ratingView = new RatingView(testFood);

        // Create the RatingOutputBoundary (Presenter) and the RatingInputBoundary (Interactor)
        RatingOutputBoundary ratingPresenter = new RatingPresenter(new RatingViewModel(), new ViewManagerModel(), new HomeViewModel());
        RatingInputBoundary ratingInteractor = new RatingInteractor(ratingPresenter);

        // Create RatingController and set it on the RatingView
        RatingController ratingController = new RatingController(ratingInteractor);
        ratingView.setController(ratingController);

        // Create a JFrame to display the RatingView
        JFrame frame = new JFrame("Rating View Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.add(ratingView); // Add RatingView to the frame
        frame.setVisible(true); // Make the frame visible
    }
}

