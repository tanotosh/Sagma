package view;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YourFoodsMatchesPage extends JPanel {
    public YourFoodsMatchesPage(User user) {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(green);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(green);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Current food field
        JLabel foodLabel = new JLabel("Your Current Food: ");
        foodLabel.setForeground(darkGreen);
        foodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel foodDisplay = new JLabel("Poutine with Bacon");
        foodDisplay.setBackground(pink);
        foodDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Matches field
        JLabel matchesLabel = new JLabel("Your Current Matches: ");
        matchesLabel.setForeground(darkGreen);
        matchesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel matchesDisplay = new JLabel("Shannon, Abeera, Gaia");
        matchesDisplay.setBackground(pink);
        matchesDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels with centered content
        JPanel foodPanel = createCenteredPanel(foodLabel, foodDisplay, green);
        JPanel matchesPanel = createCenteredPanel(matchesLabel, matchesDisplay, green);

        // Add text field
        JTextField apiPanel = new JTextField("Nutrition info displayed here");
        apiPanel.setMaximumSize(new Dimension(400, 60));
        apiPanel.setForeground(darkGreen);
        apiPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nutrition button
        JButton nutritionButton = createStyledButton("View Nutrition Facts", brown, pink);
        nutritionButton.setBackground(brown);
        nutritionButton.setForeground(pink);
        nutritionButton.setOpaque(true);
        nutritionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        nutritionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiPanel.setText("HI");
            }
        });

        // Home button
        JButton homeButton = createStyledButton("Home", brown, pink);
        homeButton.setBackground(brown);
        homeButton.setForeground(pink);
        homeButton.setOpaque(true);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = YourFoodsMatchesPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });

        // Add components with proper vertical spacing
        mainPanel.add(Box.createVerticalStrut(20)); // Top margin
        mainPanel.add(foodPanel);
        mainPanel.add(Box.createVerticalStrut(20));  // Space between food and matches
        mainPanel.add(matchesPanel);
        mainPanel.add(Box.createVerticalStrut(30)); // Space before text field
        mainPanel.add(nutritionButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Space before text field

        mainPanel.add(apiPanel);
        mainPanel.add(Box.createVerticalStrut(30));  // Space before button
        mainPanel.add(homeButton);

        mainPanel.add(Box.createVerticalStrut(20));  // Bottom margin

        add(mainPanel);
    }

    private static JPanel createCenteredPanel(JLabel label1, JLabel label2, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(bgColor);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);
        panel.add(label2);

        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }
}
