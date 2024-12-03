package view;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The view for the user homepage
 */

public class HomeScreen extends JPanel {
    private static User currentUser;

    public HomeScreen() {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Text panel with better formatting
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(green);
        GridBagConstraints textConstraints = new GridBagConstraints();

        // Username formatting
        JLabel usernameText = new JLabel("Username: Peppapig14");
        usernameText.setFont(new Font("Arial", Font.PLAIN, 20));
        textConstraints.gridx = 0;
        textConstraints.gridy = 0;
        textConstraints.anchor = GridBagConstraints.WEST;
        textConstraints.insets = new Insets(0, 20, 0, 20);
        textPanel.add(usernameText, textConstraints);

        // Rating formatting
        JLabel ratingText = new JLabel("Your rating: ★★★★☆");
        ratingText.setFont(new Font("Dialog", Font.PLAIN, 20));
        textConstraints.gridy = 1;
        textConstraints.insets = new Insets(0, 20, 0, 20);
        textPanel.add(ratingText, textConstraints);

        Dimension buttonSize = new Dimension(175, 30);

        // Create buttons with action listeners
        JButton yourFoodsButton = createStyledButton("Your foods", brown, pink, buttonSize);
        yourFoodsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "FOODS");
        });

        JButton uploadButton = createStyledButton("Upload food", brown, pink, buttonSize);
        uploadButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "UPLOAD");
        });

        JButton matchesButton = createStyledButton("Your matches", brown, pink, buttonSize);
        matchesButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "MATCHES");
        });

        JButton dietaryRestButton = createStyledButton("Dietary restrictions", brown, pink, buttonSize);
        dietaryRestButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "DIETARY");
        });

        JButton swipingButton = createStyledButton("Start swiping!", brown, pink, buttonSize);
        swipingButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "SEARCH");
        });

        // Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(green);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(yourFoodsButton, c);

        c.gridx = 1;
        buttonPanel.add(uploadButton, c);

        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(matchesButton, c);

        c.gridx = 1;
        buttonPanel.add(dietaryRestButton, c);

        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        buttonPanel.add(swipingButton, c);

        add(textPanel);
        add(buttonPanel);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setPreferredSize(size);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public static User getCurrentUser(){
        return currentUser;
    }
}