package app;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import entity.Food;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.rating.RatingController;
import interface_adapter.rating.RatingPresenter;
import interface_adapter.rating.RatingViewModel;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
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

        // Gaia changes trying to connect ratings stuff


        // Create the Use Case Interactor and Output Boundary (Presenter)
        RatingOutputBoundary ratingPresenter = new RatingPresenter(new RatingViewModel(), new ViewManagerModel(), new HomeViewModel());
        RatingInputBoundary ratingInteractor = new RatingInteractor(ratingPresenter);

        // Create RatingController
        RatingController ratingController = new RatingController(ratingInteractor);

        User foodOwner = new User(2, "Gaia", "gaia@email.com", "password", 5.0f, 5, Arrays.asList("vegan"), null);
        Food food = new Food("pasta", foodOwner, 3, "cheese", Arrays.asList("vegan"), null, "italian");

        RatingView ratingView = new RatingView(food);

        // Profile Use Case code
        UserDAO userDAO = new UserDAO(new DatabaseDAO());
        ProfileViewModel profileViewModel = new ProfileViewModel();
        ProfileOutputBoundary profilePresenter = new ProfilePresenter(new ViewManagerModel(), profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor(profilePresenter, userDAO);
        ProfileController profileController = new ProfileController(profileInteractor);
        ProfileView profileView = new ProfileView(new User("test","email@email.com","passwrod123"), profileViewModel);


//        Food uploadedFood = SwipingView.getCurrentFood();
//        RatingView ratingView = null;
//        if (uploadedFood != null) {
//            // Pass the Food object to RatingView constructor
//            ratingView = new RatingView(uploadedFood);  // Pass Food to the RatingView constructor
//            ratingView.setController(ratingController); // Set the controller for communication
//        } I will eventually need to be able to get the food from swiping page. - Gaia

        // Pass RatingController to RatingView (for communication)
        ratingView.setController(ratingController); // You need to create a setter in RatingView to allow this.


        // Add pages to main panel
        mainPanel.add(loginView, "LOGIN");
        mainPanel.add(signupView, "SIGNUP");
        mainPanel.add(searchPageView, "SEARCH");
        mainPanel.add(homePage, "HOME");
        mainPanel.add(dietaryViewPage, "DIETARY");
        mainPanel.add(swipingView, "SWIPE");
        mainPanel.add(matchView, "MATCH");
        mainPanel.add(ratingView, "RATING");
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
