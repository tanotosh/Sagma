package view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The swiping page
 */

public class SwipingPage extends JPanel {
    public SwipingPage() {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,	61);
        Color pink = new Color(234,	223,	214);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleText = new JLabel("<html><b>Poutine</b> by <b>Twilightsparkles23</b></html>\n");
        titleText.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel ratingText = new JLabel("Rating: ★★★★★");
        ratingText.setFont(new Font("Dialog", Font.PLAIN, 16));

        JLabel ingredientsText = new JLabel("Ingredients: Fries, Gravy, Cheese");
        ingredientsText.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton yesButton = new JButton("Yes :)");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = SwipingPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "MATCH");
                }
            }
        });
        yesButton.setBackground(brown);
        yesButton.setForeground(pink);

        JButton noButton = new JButton("No :(");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = SwipingPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "SWIPE");
                }
            }
        });
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

        add(mainpanel);
    }
}
