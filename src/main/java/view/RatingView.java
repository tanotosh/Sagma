package view;

import entity.Food;
import entity.User;
import interface_adapter.rating.RatingController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * The view when a user is asked for a rating
 */

public class RatingView extends JPanel {
    private RatingController ratingController;
    private Food food;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RatingView(Food food) {
        this.food = food;

        initializeView();
    }
    private void initializeView() {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,	61);
        Color pink = new Color(234,	223,	214);
//
//        User user = new User("123", "temp@gmail.com", "password"); // THIS IS TEMPORARY WHILE THINGS ARENT CONNECTED
//        Food food = new Food("poutine", user,5, "fries, gravy", Arrays.asList("dietary restrictions"), "/home/gaia/University of Toronto/Courses/CSC 207/PoutinePicture.jpg", "category");

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleText = new JLabel("What did you think of the meal?");
        titleText.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel reviewText = new JLabel("Leave a rating from 1 to 5 inclusive.");
        reviewText.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel starsPanel = new JPanel();
        starsPanel.setBackground(green);
        starsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        for (int i = 1; i <= 5; i++) {
            JButton starButton = new JButton(i + " Star" + (i > 1 ? "s" : ""));
            starButton.setFont(new Font("Arial", Font.PLAIN, 12));
            starButton.setBackground(brown);
            starButton.setForeground(pink);

            int rating = i;
            starButton.addActionListener(e -> {
                starButton.setBackground(pink);
                starButton.setForeground(brown);
                if (ratingController != null) {
                    ratingController.execute(food, rating);
                }
            });

            starsPanel.add(starButton);
        }

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 200)); // Set the size of the image
        imageLabel.setOpaque(true);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Load the image from the food object
        String imagePath = food.getImagePath();  // Assuming `Food` class has a `getImagePath()` method
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(img);
            imageLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Error loading image");
        }


        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(green);
        mainpanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        mainpanel.add(titleText, c);

        c.gridy = 1;
        mainpanel.add(imageLabel, c);

        c.gridy = 2;
        mainpanel.add(reviewText, c);

        c.gridy = 3;
        mainpanel.add(starsPanel, c);


        add(mainpanel);
    }

    public void setController(RatingController controller) {
        this.ratingController = controller;
    }
}
