package view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The swiping page
 */

public class SwipingPage extends JFrame {
    public static void main(String[] args) {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,	61);
        Color pink = new Color(234,	223,	214);

        JLabel titleText = new JLabel("<html><b>Poutine</b> by <b>Twilightsparkles23</b></html>\n");
        titleText.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel ratingText = new JLabel("Rating: ★★★★★");
        ratingText.setFont(new Font("Dialog", Font.PLAIN, 16));

        JLabel ingredientsText = new JLabel("Ingredients: Fries, Gravy, Cheese");
        ingredientsText.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton yesButton = new JButton("Yes :)");
        yesButton.setBackground(brown);
        yesButton.setForeground(pink);

        JButton noButton = new JButton("No :(");
        noButton.setBackground(brown);
        noButton.setForeground(pink);

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
        mainpanel.add(ratingText, c);

        c.gridx = 1;
        c.gridy = 3;
        mainpanel.add(ingredientsText, c);

        c.gridx = 2;
        c.gridy = 0;
        mainpanel.add(yesButton, c);

        JFrame frame = new JFrame("Swiping Page");
        frame.setSize(500, 400);
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
