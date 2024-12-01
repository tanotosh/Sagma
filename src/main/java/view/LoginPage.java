package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class LoginPage extends JFrame {
    public static void main(String[] args) {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

        // Create the main frame
        JFrame frame = new JFrame("Log In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBackground(green);

        // Configure GBC
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(0, 10, 0, 10);

        // Welcome Text
        // Load Faster One font
        try {
            Font fasterOne = Font.createFont(
                    Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/FasterOne-Regular.ttf")
            ).deriveFont(23f);

            // Create a panel for the welcome text with FlowLayout
            JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            welcomePanel.setBackground(green);

            // Split the welcome text into parts
            JLabel welcomeToLabel = new JLabel("Welcome to ");
            welcomeToLabel.setForeground(darkGreen);
            JLabel swapLabel = new JLabel("SWAP");
            swapLabel.setForeground(darkGreen);
            JLabel bySagmaLabel = new JLabel(" by Sagma");
            bySagmaLabel.setForeground(darkGreen);

            // Set the Faster One font only for SWAP
            swapLabel.setFont(fasterOne);

            // Add all parts to the welcome panel
            welcomePanel.add(welcomeToLabel);
            welcomePanel.add(swapLabel);
            welcomePanel.add(bySagmaLabel);

            // Add welcome panel to main panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            mainPanel.add(welcomePanel, gbc);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }


        // Tagline Text You’ll never stop swiping for supper!
        JLabel taglineLabel = new JLabel("You’ll never stop swiping for supper!");
        taglineLabel.setForeground(darkGreen);
        gbc.gridy = 1;
        mainPanel.add(taglineLabel, gbc);


        // Username
        JTextField usernameField = new JTextField("Username", 20);
        usernameField.setForeground(green);
        usernameField.setBackground(pink);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10, 0, 10); // More space above username
        mainPanel.add(usernameField, gbc);

        usernameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(usernameField.getText().equals("Username")){
                    usernameField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if(usernameField.getText().equals("")){
                    usernameField.setText("Username");
                }
            }
        });

        // Password
        JTextField passwordField = new JTextField("Password", 20);
        passwordField.setForeground(green);
        passwordField.setBackground(pink);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 10); // More space above password
        mainPanel.add(passwordField, gbc);

        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(passwordField.getText().equals("Password")){
                    passwordField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if(passwordField.getText().equals("")){
                    passwordField.setText("Password");
                }
            }
        });

        // Signup button
        JButton signupButton = new JButton("Sign up");
        signupButton.setBackground(brown);
        signupButton.setForeground(pink);
        signupButton.setOpaque(true);
        gbc.insets = new Insets(15, 0, 0, 0); // More space above password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Important: reset gridwidth to 1
        gbc.weightx = 0.50; // Give each button equal horizontal weight

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(brown);
        loginButton.setForeground(pink);
        loginButton.setOpaque(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(green);
        buttonPanel.add(signupButton);
        buttonPanel.add(loginButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        // Pack frame
        frame.add(mainPanel);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}