package view;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The view when a match is made
 */

public class MatchPage extends JPanel {
    public MatchPage() {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86,    61);
        Color pink = new Color(234, 223,   214);
        Color darkGreen = new Color(40,54,24);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel matchText = new JLabel("It's a match!");
        matchText.setFont(new Font("Arial", Font.BOLD, 20));

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = MatchPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "REVIEW");
                }
            }
        });
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

        add(mainpanel);
    }
}