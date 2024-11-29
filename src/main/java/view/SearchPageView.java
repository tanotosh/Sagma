package view;

import use_case.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The View for the Search Page
 */

public class SearchPageView {
    public static void main(String[] args) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(164, 179,148));
        GridBagConstraints c = new GridBagConstraints();

        //title panel
        JLabel title = new JLabel("What are you looking for?");
        title.setForeground(new Color(40, 54, 24));
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(title, c);

        //dropdown panel
        List<String> choices = Search.getCuisines();
        JComboBox<String> options = new JComboBox<String>((ComboBoxModel<String>) choices);
        options.setFont(new Font("Arial", Font.PLAIN, 13));
        options.setBackground(new Color(234, 223, 214));
        options.setForeground(new Color(123,86,61));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 50;
        panel.add(options, c);

        //button panel
        JButton button = new JButton("search");
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(new Color(123,86,61));
        button.setForeground(new Color(234,223,214));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 20;
        panel.add(button, c);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JFrame frame = new JFrame("Search Page");
        frame.setSize(500, 400);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}







