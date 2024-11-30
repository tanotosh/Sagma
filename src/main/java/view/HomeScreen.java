package view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The view for the user homepage
 */

public class HomeScreen {
    public static void main(String[] args) {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,    61);
        Color pink = new Color(234, 223,   214);

        JLabel usernameText = new JLabel("<html>Username: <b>Peppapig14</b></html>\n");
        usernameText.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ratingText = new JLabel("Your rating: ★★★★☆");
        ratingText.setFont(new Font("Dialog", Font.PLAIN, 16));
        ratingText.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(150, 30);

        JButton yourFoodsButton = new JButton("Your foods");
        yourFoodsButton.setBackground(brown);
        yourFoodsButton.setForeground(pink);
        yourFoodsButton.setPreferredSize(buttonSize);

        JButton uploadButton = new JButton("Upload food");
        uploadButton.setBackground(brown);
        uploadButton.setForeground(pink);
        uploadButton.setPreferredSize(buttonSize);

        JButton matchesButton = new JButton("Your matches");
        matchesButton.setBackground(brown);
        matchesButton.setForeground(pink);
        matchesButton.setPreferredSize(buttonSize);

        JButton dietaryRestButton = new JButton("Dietary restrictions");
        dietaryRestButton.setBackground(brown);
        dietaryRestButton.setForeground(pink);
        dietaryRestButton.setPreferredSize(buttonSize);

        JButton swipingButton = new JButton("Start swiping!");
        swipingButton.setBackground(brown);
        swipingButton.setForeground(pink);
        swipingButton.setPreferredSize(buttonSize);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(green);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(green);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 50));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);

        textPanel.add(usernameText, c);
        textPanel.add(ratingText, c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(green);
        buttonPanel.setLayout(new GridBagLayout());

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        mainPanel.add(textPanel);
        mainPanel.add(buttonPanel);

        JFrame frame = new JFrame("User Homepage");
        frame.setSize(500, 400);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}