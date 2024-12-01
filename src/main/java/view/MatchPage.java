package view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * The view when a match is made
 */

public class MatchPage extends JFrame {
    public static void main(String[] args) {

        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,    61);
        Color pink = new Color(234, 223,   214);

        JLabel matchText = new JLabel("It's a match!");
        matchText.setFont(new Font("Arial", Font.BOLD, 20));

        JButton continueButton = new JButton("Continue");
        continueButton.setBackground(brown);
        continueButton.setForeground(pink);

        JPanel whiteSquare = new JPanel();
        whiteSquare.setBackground(Color.WHITE);
        whiteSquare.setPreferredSize(new Dimension(200, 200));

        JPanel mainpanel = new JPanel();
        mainpanel.setBackground(green);
        mainpanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        mainpanel.add(whiteSquare, c);

        c.gridx = 0;
        c.gridy = 1;
        mainpanel.add(matchText, c);

        c.gridx = 0;
        c.gridy = 2;
        mainpanel.add(continueButton, c);

        JFrame frame = new JFrame("Match Page");
        frame.setSize(500, 400);
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}