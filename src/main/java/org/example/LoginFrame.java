package org.example;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Login frame responsible for registering and signing in users.
 * @author Daniel Oh
 * @version 2.0
 */
public class LoginFrame extends JFrame {
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel passwordLabel, usernameLabel, message;
    private JButton signInButton, registerButton;

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

        /** Registers new users by creating a unique JSON file */
        registerButton = new JButton("Register");
        registerButton.setBounds(420,300,100,40);
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 12));
        /**
         * @param ActionEvent register button
         */
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                Boolean isDeveloper = false;

                // TODO: Add customPOI field
                User user = new User(username, password, isDeveloper);
                JSONObject userJSONObject = user.toJSONObject();

                try {
                    FileWriter file = new FileWriter("./data/userData/" + username + ".json");
                    file.write(userJSONObject.toString());
                    file.flush();
                    file.close();
                    JOptionPane.showMessageDialog(LoginFrame.this, "Account created successfully!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error creating account!");
                    ex.printStackTrace();
                }
            }
        });

        /** Verifies username and password
         * @param ActionEvent Sign in button
         * */
        signInButton = new JButton("Sign In");
        signInButton.setBounds(300,300,100,40);
        signInButton.setFont(new java.awt.Font("Segoe UI", 0, 12));
        signInButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    char[] passwordChars = passwordField.getPassword();
                    String password = new String(passwordChars);

                    // Check if the user exists and if their password is correct
                    try {
                        File file = new File("./data/userData/" + username + ".json");
                        if (file.exists()) {
                            String userStr = new String(Files.readAllBytes(file.toPath()));
                            JSONObject user = new JSONObject(userStr);
                            if (user.getString("password").equals(password)) {
                                JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                                dispose();
                                LandingPage frame = new LandingPage();
                            } else {
                                JOptionPane.showMessageDialog(LoginFrame.this, "Incorrect password!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(LoginFrame.this, "User does not exist!");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Error reading user data!");
                        ex.printStackTrace();
                    } catch (JSONException ex) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Error parsing user data!");
                        ex.printStackTrace();
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