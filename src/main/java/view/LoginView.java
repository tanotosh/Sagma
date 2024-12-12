package view;

import data_access.DatabaseDAO;
import data_access.UserDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.session.LoginSessionState;
import use_case.login.LoginInteractor;

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

public class LoginView extends JPanel {
    public LoginView() {
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

        // Signup button
        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = LoginView.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "SIGNUP");
                }
            }
        });
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = passwordField.getText().trim();

                // Initialize backend components
                UserDAO userDAO = new UserDAO(new DatabaseDAO()); // Replace with actual DAO implementation
                LoginSessionState sessionState = new LoginSessionState();
                LoginPresenter loginPresenter = new LoginPresenter(
                        new ViewManagerModel(), // Replace with your actual ViewManagerModel
                        new LoginViewModel()
                );
                LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, userDAO, sessionState);
                LoginController loginController = new LoginController(loginInteractor);

                // Call the controller
                loginController.execute(email, password);

                // Navigate to Home on success
                Container parent = LoginView.this.getParent();
                if (parent != null) {
                    CardLayout cl = (CardLayout) parent.getLayout();
                    cl.show(parent, "HOME");
                }
            }
        });
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
        add(mainPanel);
    }

}
