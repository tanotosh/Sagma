package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import api.NutritionAPI;
import entity.User;
import interface_adapter.login.LoginState;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

/**
 * ProfileView displays user profile information.
 * @param <T> the type of user
 */
public class ProfileView<T> extends JPanel {
    /** Maximum width for components. */
    private static final int MAX_WIDTH = 400;
    /** Standard vertical spacing. */
    private static final int SPACING_SMALL = 20;
    /** Larger vertical spacing. */
    private static final int SPACING_LARGE = 30;
    /** RGB value for green. */
    private static final Color GREEN = new Color(164, 179, 148);
    /** RGB value for brown. */
    private static final Color BROWN = new Color(123, 86, 61);
    /** RGB value for pink. */
    private static final Color PINK = new Color(234, 223, 214);
    /** RGB value for dark green. */
    private static final Color DARK_GREEN = new Color(40, 54, 24);

    /**
     * Constructs a new ProfileView.
     * @param user The user whose profile to display
     */
    public ProfileView(final User user) {
        initializeView();
    }
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileView extends JPanel {
    private final ProfileViewModel profileViewModel;

    private final String viewName = "profile";
    private final JLabel foodDisplay = new JLabel();
    private final JLabel matchesDisplay = new JLabel();

    private ProfileController profileController;

    public ProfileView(User user, ProfileViewModel profileViewModel) {
        this.profileViewModel = profileViewModel;

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

    private void initializeView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(GREEN);

        JLabel foodDisplay = new JLabel("Poutine with Bacon");
        foodDisplay.setBackground(PINK);
        JPanel mainPanel = createMainPanel();
        JPanel foodPanel = createFoodPanel(foodDisplay);
        JPanel matchesPanel = createMatchesPanel();
        JTextField apiPanel = createApiPanel();


        JButton nutritionButton = createNutritionButton(foodDisplay,apiPanel);
        JButton homeButton = createHomeButton();
        assembleMainPanel(mainPanel, foodPanel, matchesPanel,
                apiPanel, nutritionButton, homeButton);
        add(mainPanel);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(GREEN);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private JPanel createFoodPanel(JLabel foodDisplay) {
        JLabel foodLabel = new JLabel("Your Current Food: ");
        foodLabel.setForeground(DARK_GREEN);
        return createCenteredPanel(foodLabel, foodDisplay, GREEN);
    }

    private JPanel createMatchesPanel() {
        JLabel matchesLabel = new JLabel("Your Current Matches: ");
        matchesLabel.setForeground(DARK_GREEN);
        JLabel matchesDisplay = new JLabel("Shannon, Abeera, Gaia");
        matchesDisplay.setBackground(PINK);
        return createCenteredPanel(matchesLabel, matchesDisplay, GREEN);
    }
        setBackground(green);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(green);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Current food field
        final JLabel foodLabel = new JLabel("Your Current Food:");
        foodLabel.setForeground(darkGreen);
        foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        foodDisplay.setBackground(pink);
        foodDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Matches field
        final JLabel matchesLabel = new JLabel("Your Current Matches:");
        matchesLabel.setForeground(darkGreen);
        matchesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        matchesDisplay.setBackground(pink);
        matchesDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels with centered content
        final JPanel foodPanel = createCenteredPanel(foodLabel, foodDisplay, green);
        final JPanel matchesPanel = createCenteredPanel(matchesLabel, matchesDisplay, green);

        // Home button
        final JButton homeButton = new JButton("Home");
        homeButton.setBackground(brown);
        homeButton.setForeground(pink);
        homeButton.setOpaque(true);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = ProfileView.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });

    private JTextField createApiPanel() {
        JTextField panel = new JTextField("Nutrition info displayed here");
        panel.setMaximumSize(new Dimension(MAX_WIDTH, SPACING_LARGE));
        panel.setForeground(DARK_GREEN);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    private void assembleMainPanel(JPanel mainPanel, JPanel foodPanel,
                                   JPanel matchesPanel, JTextField apiPanel,
                                   JButton nutritionButton, JButton homeButton) {
        mainPanel.add(Box.createVerticalStrut(SPACING_SMALL));
        mainPanel.add(foodPanel);
        mainPanel.add(Box.createVerticalStrut(SPACING_SMALL));
        mainPanel.add(matchesPanel);
        mainPanel.add(Box.createVerticalStrut(SPACING_LARGE));
        mainPanel.add(nutritionButton);
        mainPanel.add(Box.createVerticalStrut(SPACING_SMALL));
        mainPanel.add(apiPanel);
        mainPanel.add(Box.createVerticalStrut(SPACING_LARGE));
        mainPanel.add(homeButton);
        mainPanel.add(Box.createVerticalStrut(SPACING_SMALL));
    }

    private static JPanel createCenteredPanel(final JLabel label1,
                                              final JLabel label2, final Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(bgColor);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);
        panel.add(label2);
        return panel;
    }

    private JButton createStyledButton(final String text,
                                       final Color bgColor, final Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JButton createNutritionButton(final JLabel foodDisplay, final JTextField apiPanel) {
        JButton button = createStyledButton("View Nutrition Facts", BROWN, PINK);
        button.addActionListener(event -> {
            String query = foodDisplay.getText().trim();
            if (!query.isEmpty()) {
                String nutritionInfo = NutritionAPI.getNutritionInfo(query);
                apiPanel.setText(nutritionInfo);
            } else {
                apiPanel.setText("Please enter a food item");
            }
        });
        return button;
    }


    private JButton createHomeButton() {
        JButton button = createStyledButton("Home", BROWN, PINK);
        button.addActionListener(event -> {
            Container parent = ProfileView.this.getParent();
            if (parent != null) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, "HOME");
            }
        });
        return button;
    }
}
