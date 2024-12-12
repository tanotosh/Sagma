package view;

import entity.User;
import interface_adapter.login.LoginState;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;

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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

        // Add components with vertical spacing
        add(Box.createVerticalStrut(200));  // Add space at top
        mainPanel.add(foodPanel);
        mainPanel.add(Box.createVerticalStrut(100));  // Space between food and matches
        mainPanel.add(matchesPanel);
        mainPanel.add(Box.createVerticalStrut(100));  // Space before button
        mainPanel.add(homeButton);
        add(mainPanel);
        add(Box.createVerticalStrut(200));  // Add space at bottom
    }

    private static JPanel createCenteredPanel(JLabel label1, JLabel label2, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(bgColor);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createHorizontalGlue());
        panel.add(label1);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(label2);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

}