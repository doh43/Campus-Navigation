package org.example;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        /** Register new users */
        registerButton = new JButton("Register");
        registerButton.setBounds(420,300,100,40);
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 12));
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                Boolean isDeveloper = false;

                User user = new User(username, password, isDeveloper);
                JSONObject userJSONObject = user.toJSONObject();
                /*
                // Create a new user object and write it to a JSON file
                JSONObject user = new JSONObject();
                user.put("username", username);
                user.put("password", password);
                user.put("isDeveloper", false);
                // TODO: perhaps make this a method in user and call it here

                // TODO: Add favourites array to JSON
                // TODO: Add custom POI array to JSON
                */

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

        /** Login verification */
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