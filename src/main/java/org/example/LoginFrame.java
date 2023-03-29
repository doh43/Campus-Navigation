package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import com.fasterxml.jackson.databind.*;

public class LoginFrame extends JFrame {
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel passwordLabel, usernameLabel, message;
    private JButton button, registerButton;

    public LoginFrame() {
        super("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,500);
        this.setTitle("Western Campus Navigation");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(225,200,100,40);
        usernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(225,250,100,40);
        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));

        usernameField = new JTextField();
        usernameField.setBounds(300,200,300,40);

        passwordField = new JPasswordField();
        passwordField.setBounds(300,250,300,40);

        // Add a button to create a new user account
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(420,300,100,40);
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 12));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean isDeveloper = false;

                // Create a new user object and write it to a JSON file
                User user = new User(username, password, isDeveloper);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    mapper.writeValue(new File("./data/userData/" + username + ".json"), user);
                    JOptionPane.showMessageDialog(LoginFrame.this, "Account created successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error creating account!");
                    ex.printStackTrace();
                }
            }
        });
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(300,300,100,40);
        signInButton.setFont(new java.awt.Font("Segoe UI", 0, 12));

        signInButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);


                // Check if the user exists and if their password is correct
                ObjectMapper mapper = new ObjectMapper();
                try {
                    User user = mapper.readValue(new File("./data/userData/" + username + ".json"), User.class);
                    if (user.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                        dispose();
                        LandingPage frame = new LandingPage();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect password!");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "User does not exist!");
                }
            }
        });
        message = new JLabel();
        message.setBounds(300,350,300,40);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(signInButton);
        this.add(registerButton);
        this.add(message);

        // Add the components to the login frame-
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            }
        });
    }
}