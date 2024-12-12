package app;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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
import view.*;

public class SwapAppBuilder {
    private static JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public SwapAppBuilder() {
        mainFrame = new JFrame("SWAP by Sagma");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);


        // Initialize pages as JPanels
        LoginView loginView = new LoginView();
        HomeView homePage = new HomeView();
        DietaryView dietaryViewPage = new DietaryView(new User("test","email@email.com","passwrod123"));
        SignupView signupView = new SignupView();
        SearchPageView searchPageView = new SearchPageView();
        SwipingView swipingView = new SwipingView(new User("test","email@email.com","passwrod123"));
        MatchView matchView = new MatchView();
        UploadFoodView uploadFoodView = new UploadFoodView();
        ProfileView profileView = new ProfileView(new User("test","email@email.com","passwrod123"));

//        // first 2 lines aren't necessary once all tied in together
//        User foodOwner = new User(2, "Gaia", "gaia@email.com", "password", 5.0f, 5, Arrays.asList("vegan"), null);
//        Food tempfood = new Food("pasta", foodOwner, 3, "cheese", Arrays.asList("vegan"), null, "italian");
//        RatingOutputBoundary ratingPresenter = new RatingPresenter();
//        RatingInputBoundary ratingInteractor = new RatingInteractor(ratingPresenter);
//        RatingController ratingController = new RatingController(ratingInteractor);
//        RatingView ratingView = new RatingView(tempfood, )

        // Create the Use Case Interactor and Output Boundary (Presenter) for Rating Use Case (Gaia)
//        RatingOutputBoundary ratingPresenter = new RatingPresenter();
//        RatingInputBoundary ratingInteractor = new RatingInteractor(ratingPresenter);
//
//        // Create RatingController
//        RatingController ratingController = new RatingController(ratingInteractor);
//
//        // Requires SwipingView to have getCurrentFood() method which returns the matched food.
//        Food food = matchView.getCurrentFood();
//        RatingView ratingView = null;
//
//        if (food != null) {
//            // Pass the Food object to RatingView constructor
//            ratingView = new RatingView(food, ratingController);
//            ratingView.setController(ratingController);
//        }

        // Add pages to main panel
        mainPanel.add(loginView, "LOGIN");
        mainPanel.add(signupView, "SIGNUP");
        mainPanel.add(searchPageView, "SEARCH");
        mainPanel.add(homePage, "HOME");
        mainPanel.add(dietaryViewPage, "DIETARY");
        mainPanel.add(swipingView, "SWIPE");
        mainPanel.add(matchView, "MATCH");
//        mainPanel.add(ratingView, "RATING");
        mainPanel.add(uploadFoodView, "UPLOAD");
        mainPanel.add(profileView,"FOODS");

        // Setup main frame
        mainFrame.add(mainPanel);
        mainFrame.setSize(1000, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "LOGIN");
        mainFrame.setVisible(true);

    }
}
