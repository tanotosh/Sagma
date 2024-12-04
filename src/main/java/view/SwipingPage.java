package view;

import data_access.DataManager;
import entity.Food;
import entity.User;
import use_case.Swiping;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The swiping page
 */


public class SwipingPage extends JPanel{
    private User user;

    public SwipingPage(User user) {
        this.user = user;

//        List<String> foodsList = new ArrayList<>(); //
//        foodsList.add("Poutine"); //
//        foodsList.add("Ramen"); //
//        foodsList.add("Sushi"); //
//        foodsList.add("Sushi"); //
//        foodsList.add("Soup"); //
//        foodsList.add("Shawarma"); //
//        foodsList.add("Tacos"); //

        List<Food> foodsList= (List<Food>) SearchPageView.getFilteredFoods();


        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,    61);
        Color pink = new Color(234, 223,   214);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Integer[] index = {0};
        Swiping userFoodPair = new Swiping(user, foodsList.get(index[0]));
         JLabel titleText = new JLabel(foodsList.get(index[0]).getName() + " by " + foodsList.get(index[0]).getOwner().getName());
        titleText.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel ratingText = new JLabel("User Rating: " + foodsList.get(index[0]).getOwner().getRating());
        ratingText.setFont(new Font("Dialog", Font.PLAIN, 16));

        JLabel ingredientsText = new JLabel("Ingredients: " + foodsList.get(index[0]).getIngredients());
        ingredientsText.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton yesButton = new JButton("Yes :)");
        yesButton.setBackground(brown);
        yesButton.setForeground(pink);

        JButton noButton = new JButton("No :(");
        noButton.setBackground(brown);
        noButton.setForeground(pink);

        noButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                index[0]++;

                userFoodPair.currentfood = foodsList.get(index[0]);
                if (!userFoodPair.checkFood()) {
                    do {
                        index[0]++;
                        userFoodPair.currentfood = foodsList.get(index[0]);
                    } while (!userFoodPair.checkFood());
                }
                titleText.setText(foodsList.get(index[0]).getName() + " by " + foodsList.get(index[0]).getOwner().getName());
                ratingText.setText("User Rating: " + foodsList.get(index[0]).getOwner().getRating());
                ingredientsText.setText("Ingredients: " + foodsList.get(index[0]).getIngredients());
            }
        });

        yesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                userFoodPair.swipeRight();
                if (userFoodPair.checkMatch()) {
                    try {
                        userFoodPair.matchMade();
                        Container parent = SwipingPage.this.getParent();
                        if (parent != null) {
                            CardLayout cl = (CardLayout) parent.getLayout();
                            cl.show(parent, "MATCH");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Log or handle the exception
                        JOptionPane.showMessageDialog(null, "An error occurred while making the match.");
                    }
                } else {
                    index[0]++;
                    userFoodPair.currentfood = foodsList.get(index[0]);
                    if (!userFoodPair.checkFood()) {
                        do {
                            index[0]++;
                            userFoodPair.currentfood = foodsList.get(index[0]);
                        } while (!userFoodPair.checkFood());
                    }
                    titleText.setText(foodsList.get(index[0]).getName() + " by " + foodsList.get(index[0]).getOwner().getName());
                    ratingText.setText("User Rating: " + foodsList.get(index[0]).getOwner().getRating());
                    ingredientsText.setText("Ingredients: " + foodsList.get(index[0]).getIngredients());

                }
            }
        });

        JPanel whiteSquare = new JPanel();
        whiteSquare.setBackground(Color.WHITE);
        whiteSquare.setPreferredSize(new Dimension(200, 200));

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(green);
        mainpanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);

        c.gridx = 0;
        c.gridy = 0;
        mainpanel.add(noButton, c);

        c.gridx = 1;
        c.gridy = 0;
        mainpanel.add(whiteSquare, c);

        c.gridx = 1;
        c.gridy = 1;
        mainpanel.add(titleText, c);

        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 1;
        c.gridy = 2;
//        mainpanel.add(ratingText, c);

        c.gridx = 1;
        c.gridy = 3;
//        mainpanel.add(ingredientsText, c);

        c.gridx = 2;
        c.gridy = 0;
        mainpanel.add(yesButton, c);

        add(mainpanel);
    }
}
