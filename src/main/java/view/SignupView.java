package view;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.session.SignupSessionState;
import interface_adapter.signup.*;
import use_case.signup.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import java.awt.Font;
import java.awt.FontFormatException;

public class SignupView extends JPanel {
    public SignupView() {
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);
        Color darkGreen = new Color(40, 54, 24);

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
            JLabel welcomeToLabel = new JLabel("Signup to");
            welcomeToLabel.setForeground(darkGreen);
            JLabel swapLabel = new JLabel("SWAP");
            swapLabel.setForeground(darkGreen);

            // Set the Faster One font only for SWAP
            swapLabel.setFont(fasterOne);

            // Add all parts to the welcome panel
            welcomePanel.add(welcomeToLabel);
            welcomePanel.add(swapLabel);

            // Add welcome panel to main panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            mainPanel.add(welcomePanel, gbc);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Username
        JTextField usernameField = new JTextField("Username", 20);
        usernameField.setForeground(green);
        usernameField.setBackground(pink);
        gbc.gridy = 1; // Adjust position
        mainPanel.add(usernameField, gbc);

        usernameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (usernameField.getText().equals("")) {
                    usernameField.setText("Username");
                }
            }
        });

        // Email
        JTextField emailField = new JTextField("Email", 20);
        emailField.setForeground(green);
        emailField.setBackground(pink);
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10, 0, 10); // More space above email
        mainPanel.add(emailField, gbc);

        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if(emailField.getText().equals("Email")){
                    emailField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if(emailField.getText().equals("")){
                    emailField.setText("Email");
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

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = SignupView.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "LOGIN");
                }
            }
        });
        backButton.setBackground(brown);
        backButton.setForeground(pink);
        backButton.setOpaque(true);
        gbc.insets = new Insets(15, 0, 0, 0); // More space above password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Important: reset gridwidth to 1
        gbc.weightx = 0.50; // Give each button equal horizontal weight

        // Signup button
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Retrieve username
                String email = emailField.getText();       // Retrieve email
                String password = passwordField.getText(); // Retrieve password

                // Create backend components
                UserDAO userDAO = new UserDAO(new DatabaseDAO()); // Replace with actual DAO implementation
                SignupSessionState signupSessionState = new SignupSessionState();
                SignupPresenter signupPresenter = new SignupPresenter(
                        new ViewManagerModel(), // Pass your actual ViewManagerModel
                        new SignupViewModel(),
                        new LoginViewModel()   // Pass your actual LoginViewModel
                );
                SignupInteractor signupInteractor = new SignupInteractor(signupPresenter, userDAO, signupSessionState);
                SignupController signupController = new SignupController(signupInteractor);

                // Execute sign-up process
                signupController.execute(username, email, password, password);

                // Navigate to Home view upon success (handled by presenter logic)
                Container parent = SignupView.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });

        signupButton.setBackground(brown);
        signupButton.setForeground(pink);
        signupButton.setOpaque(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(green);
        buttonPanel.add(backButton);
        buttonPanel.add(signupButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        // Pack frame
        add(mainPanel);
    }

}