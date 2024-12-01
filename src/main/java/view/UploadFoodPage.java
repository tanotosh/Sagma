package view;

import entity.User;
import use_case.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Arrays;
import java.util.List;

/**
 * The view when a user is asked for a rating
 */

public class UploadFoodPage extends JFrame {
    public static void main(String[] args) {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40,54,24);

        User user = new User("123", "temp@gmail.com", "password"); // THIS IS TEMPORARY WHILE THINGS ARENT CONNECTED

        // Create the main frame
        JFrame frame = new JFrame("Upload New Food");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(green);
        GridBagConstraints gbc = new GridBagConstraints();

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1, 0, 25));
        leftPanel.setBackground(green);

        // Food name field
        JLabel nameLabel = new JLabel("Food name:");
        nameLabel.setForeground(darkGreen);
        JTextField nameField = new JTextField(20);
        nameField.setBackground(pink);

        // Ingredients field
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        JTextField ingredientsField = new JTextField(20);
        ingredientsLabel.setForeground(darkGreen);
        ingredientsField.setBackground(pink);

        // Servings field
        JLabel servingsLabel = new JLabel("Number of servings:");
        String[] servingOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
        JComboBox<String> servingsBox = new JComboBox<>(servingOptions);
        servingsBox.setBackground(pink);
        servingsLabel.setForeground(darkGreen);

        // Create and add input panels
        leftPanel.add(createInputPanel(nameLabel, nameField, green));
        leftPanel.add(createInputPanel(ingredientsLabel, ingredientsField, green));
        leftPanel.add(createInputPanel(servingsLabel, servingsBox, green));

        // Right panel
        JPanel rightPanel = new JPanel(new BorderLayout(0, 10));
        rightPanel.setBackground(green);

        // Image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 200));
        imagePanel.setBackground(pink);

        // Upload button
        JButton uploadButton = new JButton("Upload Food");
        uploadButton.setBackground(brown);
        uploadButton.setForeground(pink);
        uploadButton.setOpaque(true);
        uploadButton.setPreferredSize(new Dimension(200, 40));

        uploadButton.addActionListener(e -> {
            String name = nameField.getText();
            String ingredients = ingredientsField.getText();
            int quantity = Integer.parseInt((String) servingsBox.getSelectedItem());
            List<String> dietaryRestrictions = Arrays.asList("None"); // REPLACE?
            String category = "Uncategorized"; // REPLACE?

            user.uploadFood(name, quantity, ingredients, dietaryRestrictions, category);
            uploadButton.setBackground(pink);
            uploadButton.setForeground(brown);
        });

        // DO WE NEED A BACK BUTTON? Because after you upload it doesn't go anywhere. Or should it bring you back to the home page? Also need to figure out the image and the restrictions and category.

        rightPanel.add(imagePanel, BorderLayout.CENTER);
        rightPanel.add(uploadButton, BorderLayout.SOUTH);

        // Add panels to main panel
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel, gbc);

        gbc.gridx = 1;
        mainPanel.add(rightPanel, gbc);

        // Set up and display frame
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createInputPanel(JLabel label, JComponent input, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(bgColor);
        panel.add(label, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        return panel;
    }
}