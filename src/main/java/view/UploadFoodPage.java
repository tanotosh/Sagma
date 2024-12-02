package view;

import entity.User;
import use_case.Search;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * The view when a user is asked for a rating
 */

public class UploadFoodPage extends JPanel {
    public UploadFoodPage() {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40,54,24);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        User user = new User("123", "temp@gmail.com", "password"); // THIS IS TEMPORARY WHILE THINGS ARENT CONNECTED

        // Create the main frame
        JFrame frame = new JFrame("Upload New Food");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(green);
        GridBagConstraints gbc = new GridBagConstraints();

        // Create the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1, 0, 25));
        leftPanel.setBackground(green);

        // Food name field
        JLabel nameLabel = new JLabel("Food name:");
        nameLabel.setForeground(darkGreen);
        JTextField nameField = new JTextField(20);
        nameField.setBackground(pink);

        // Ingredients field
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        JTextField ingredientsField = new JTextField(20);
        ingredientsLabel.setForeground(darkGreen);
        ingredientsField.setBackground(pink);

        // Servings field
        JLabel servingsLabel = new JLabel("Number of servings:");
        String[] servingOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
        JComboBox<String> servingsBox = new JComboBox<>(servingOptions);
        servingsBox.setBackground(pink);
        servingsLabel.setForeground(darkGreen);

        // Create and add input panels to left panel
        leftPanel.add(createInputPanel(nameLabel, nameField, green));
        leftPanel.add(createInputPanel(ingredientsLabel, ingredientsField, green));
        leftPanel.add(createInputPanel(servingsLabel, servingsBox, green));

        // Add left panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(leftPanel, gbc);

        // Creating Right panel
        JPanel rightPanel = new JPanel(new BorderLayout(0, 10));
        rightPanel.setBackground(green);

        // Dietary restrictions checkbox panel
        List<String> dietaryRestrictions = new ArrayList<>();
        JPanel dietaryPanel = new JPanel(new GridLayout(3, 3, 0, 10));
        dietaryPanel.setBackground(green);

        String[] dietaryOptions = {"Vegetarian", "Vegan", "Gluten-Free", "Halal", "Dairy-free", "Kosher", "Nut-free", "Shellfish-free", "Other"};
        JCheckBox[] checkBoxes = new JCheckBox[dietaryOptions.length];

        for (int i = 0; i < dietaryOptions.length; i++) {
            checkBoxes[i] = new JCheckBox(dietaryOptions[i]);
            checkBoxes[i].setBackground(green);
            dietaryPanel.add(checkBoxes[i]);

            // adds listener to update the list dietaryRestrictions
            checkBoxes[i].addItemListener(e -> {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                if (checkBox.isSelected()) {
                    dietaryRestrictions.add(checkBox.getText());
                }
                else {
                    dietaryRestrictions.remove(checkBox.getText());
                }
            });
        }

        // add dietary panel
        JLabel dietaryLabel = new JLabel("What Dietary Restrictions does this food violate:");
        dietaryLabel.setForeground(darkGreen);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(dietaryLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(dietaryPanel, gbc);

        // Image panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(pink);

        // Label that will display image or blank
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 200));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // upload image button and listener
        JButton uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBackground(brown);
        uploadImageButton.setForeground(pink);
        uploadImageButton.setOpaque(true);

        final String[] uploadedImageFilePath = {null};
        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    uploadedImageFilePath[0] = file.getAbsolutePath();

                    BufferedImage image = ImageIO.read(file);
                    imageLabel.setIcon(new ImageIcon(image));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "ERROR: Unable to load image.");
                }
            }
        });

        // adding imageLabel and uploadImageButton to imagePanel
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(uploadImageButton, BorderLayout.SOUTH);


        //category of food panel
        JLabel title = new JLabel("What type of cuisine is this?");
        title.setForeground(darkGreen);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        mainPanel.add(title, gbc);

        //dropdown panel for category of food
        List<String> choices = Arrays.asList("Chinese", "Italian", "Mexican", "American", "Other"); // Temporary
        // List<String> choices = Search.getCategory(); for later
        JComboBox<String> options = new JComboBox<String>(choices.toArray(new String[0]));
        options.setFont(new Font("Arial", Font.PLAIN, 13));
        options.setBackground(pink);
        options.setForeground(brown);
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 50;
        mainPanel.add(options, gbc);

        // Upload button
        JButton uploadButton = new JButton("Upload Food");
        uploadButton.setBackground(brown);
        uploadButton.setForeground(pink);
        uploadButton.setOpaque(true);
        uploadButton.setPreferredSize(new Dimension(200, 40));

        // Upload Button listener
        uploadButton.addActionListener(e -> {
            String name = nameField.getText();
            String ingredients = ingredientsField.getText();
            int quantity = Integer.parseInt((String) servingsBox.getSelectedItem());

            String category = (String) options.getSelectedItem();

            user.uploadFood(name, quantity, ingredients, dietaryRestrictions, uploadedImageFilePath[0], category);
            uploadButton.setBackground(pink);
            uploadButton.setForeground(brown);
        });

        // DO WE NEED A BACK BUTTON? Because after you upload it doesn't go anywhere. Or should it bring you back to the home page? Also need to figure out the image and the restrictions and category.

        rightPanel.add(imagePanel, BorderLayout.CENTER);
        rightPanel.add(uploadButton, BorderLayout.SOUTH);

        // Add panels to main panel
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(rightPanel, gbc);


        // Set up and display frame
        add(mainPanel);
    }

    private static JPanel createInputPanel(JLabel label, JComponent input, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(bgColor);
        panel.add(label, BorderLayout.NORTH);
        panel.add(input, BorderLayout.CENTER);
        return panel;
    }
}