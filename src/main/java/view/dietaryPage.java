package view;

import entity.User;
import use_case.DietaryRestriction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class dietaryPage extends JPanel {

    private final User user;

    public dietaryPage(User user) {
        this.user = user;
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


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
                String label = source.getText();
                boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
                DietaryRestriction dietaryRestriction = new DietaryRestriction(user, label);
                if (selected) {
                    dietaryRestriction.addRestriction();
                } else {
                    dietaryRestriction.removeRestriction();
                }
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
        Dimension buttonSize = new Dimension(175, 30);
        JButton backButton = createStyledButton("Back", brown, pink, buttonSize);
        // Fix the back button action listener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = dietaryPage.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gbc);

        // Set up and display frame
        add(mainPanel);
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setPreferredSize(size);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

}

