package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class dietaryPage extends JFrame {
    public static void main(String[] args) {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

        // Create the main frame
        JFrame frame = new JFrame("Set Dietary Restrictions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configure GBC
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 10, 0, 10);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(green);

        // Title label at top
        JLabel titleLabel = new JLabel("Let us know what dietary restrictions you have:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(titleLabel, gbc);

        // Checkbox panel in the middle
        JPanel checkboxPanel = new JPanel(new GridLayout(3, 3, 0, 10));
        checkboxPanel.setBackground(green);

        JCheckBox vegetarianBox = new JCheckBox("Vegetarian");
        JCheckBox veganBox = new JCheckBox("Vegan");
        JCheckBox glutenFreeBox = new JCheckBox("Gluten-Free");
        JCheckBox HalalBox = new JCheckBox("Halal");
        JCheckBox diaryBox = new JCheckBox("Dairy-free");
        JCheckBox kosherBox = new JCheckBox("Kosher");
        JCheckBox nutBox = new JCheckBox("Nut-free");
        JCheckBox shellfishBox = new JCheckBox("Shellfish-free");
        JCheckBox otherBox = new JCheckBox("Other");

        // Style checkboxes
        vegetarianBox.setBackground(green);
        veganBox.setBackground(green);
        glutenFreeBox.setBackground(green);
        HalalBox.setBackground(green);
        diaryBox.setBackground(green);
        kosherBox.setBackground(green);
        nutBox.setBackground(green);
        shellfishBox.setBackground(green);
        otherBox.setBackground(green);

        // Add listener to handle selections
        ItemListener dietaryListener = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                // Handle the selection here
            }
        };
        // Add listeners to checkboxes
        vegetarianBox.addItemListener(dietaryListener);
        veganBox.addItemListener(dietaryListener);
        glutenFreeBox.addItemListener(dietaryListener);
        HalalBox.addItemListener(dietaryListener);
        diaryBox.addItemListener(dietaryListener);
        kosherBox.addItemListener(dietaryListener);
        nutBox.addItemListener(dietaryListener);
        shellfishBox.addItemListener(dietaryListener);
        otherBox.addItemListener(dietaryListener);

        checkboxPanel.add(vegetarianBox);
        checkboxPanel.add(veganBox);
        checkboxPanel.add(glutenFreeBox);
        checkboxPanel.add(HalalBox);
        checkboxPanel.add(diaryBox);
        checkboxPanel.add(kosherBox);
        checkboxPanel.add(nutBox);
        checkboxPanel.add(shellfishBox);
        checkboxPanel.add(otherBox);

        // Add checkbox panel
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(checkboxPanel, gbc);

        // Back button at bottom
        JButton backButton = new JButton("Back");
        backButton.setBackground(brown);
        backButton.setForeground(pink);
        backButton.setPreferredSize(new Dimension(100, 40));

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gbc);

        // Set up and display frame
        frame.add(mainPanel);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

