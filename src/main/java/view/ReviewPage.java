package view;

import entity.Food;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The view when a user is asked for a rating
 */

public class ReviewPage extends JPanel {
    public ReviewPage() {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,	61);
        Color pink = new Color(234,	223,	214);

        User user = new User("123", "temp@gmail.com", "password"); // THIS IS TEMPORARY WHILE THINGS ARENT CONNECTED
        Food food = new Food("poutine", user,5, "fries, gravy", Arrays.asList("dietary restrictions"), "/home/gaia/Photos/LunarNewYearPhotos/DSC_0563.JPG", "category");

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
                user.rateFood(food, rating);
            });

            starsPanel.add(starButton);
        }

        JPanel whiteSquare = new JPanel();
        whiteSquare.setBackground(Color.WHITE);
        whiteSquare.setPreferredSize(new Dimension(200, 200));

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = ReviewPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });
        continueButton.setBackground(brown);
        continueButton.setForeground(pink);

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(green);
        mainpanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        mainpanel.add(titleText, c);

        c.gridy = 1;
        mainpanel.add(whiteSquare, c);

        c.gridy = 2;
        mainpanel.add(reviewText, c);

        c.gridy = 3;
        mainpanel.add(starsPanel, c);

        c.gridy = 4;
        mainpanel.add(continueButton, c);

        add(mainpanel);
    }
}
