package app;

import javax.swing.*;
import java.awt.*;
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
        LoginPage loginPage = new LoginPage();
        HomeScreen homePage = new HomeScreen();
//        dietaryPage dietaryPage = new dietaryPage();
        SignupPage signupPage = new SignupPage();
        SearchPageView searchPageView = new SearchPageView();
        SwipingPage swipingPage = new SwipingPage();
        MatchPage matchPage = new MatchPage();
        ReviewPage reviewPage = new ReviewPage();
        UploadFoodPage uploadFoodPage = new UploadFoodPage();

        // Add pages to main panel
        mainPanel.add(loginPage, "LOGIN");
        mainPanel.add(signupPage, "SIGNUP");
        mainPanel.add(searchPageView, "SEARCH");
        mainPanel.add(homePage, "HOME");
//        mainPanel.add(dietaryPage, "DIETARY");
        mainPanel.add(swipingPage, "SWIPE");
        mainPanel.add(matchPage, "MATCH");
        mainPanel.add(reviewPage, "REVIEW");
        mainPanel.add(uploadFoodPage, "UPLOAD");

        // Setup main frame
        mainFrame.add(mainPanel);
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        cardLayout.show(mainPanel, "LOGIN");
        mainFrame.setVisible(true);

    }
}
