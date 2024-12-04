package app;

import javax.swing.*;
import java.awt.*;

import entity.User;
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
        SwipingView swipingView = new SwipingView();
        MatchView matchView = new MatchView();
        RatingView reviewView = new RatingView();
        UploadFoodView uploadFoodView = new UploadFoodView();
        ProfileView profileView = new ProfileView(new User("test","email@email.com","passwrod123"));

        // Add pages to main panel
        mainPanel.add(loginView, "LOGIN");
        mainPanel.add(signupView, "SIGNUP");
        mainPanel.add(searchPageView, "SEARCH");
        mainPanel.add(homePage, "HOME");
        mainPanel.add(dietaryViewPage, "DIETARY");
        mainPanel.add(swipingView, "SWIPE");
        mainPanel.add(matchView, "MATCH");
        mainPanel.add(reviewView, "REVIEW");
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
