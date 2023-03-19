package org.example;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginPage extends JFrame implements ActionListener {
    JPasswordField password;
    JTextField username;
    JLabel passwordLabel, usernameLabel, message, title;
    JButton button, resetButton;
    JCheckBox showPassword;

    LoginPage() {
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

        username = new JTextField();
        username.setBounds(300,200,300,40);

        password = new JPasswordField();
        password.setBounds(300,250,300,40);

        /*
        showPassword = new JCheckBox("Show password");
        showPassword.setBounds(300,250,300,40);
        showPassword.addActionListener(this);
        */

        button = new JButton("Sign in");
        button.setBounds(300,300,100,40);
        button.addActionListener(this);
        button.setFont(new java.awt.Font("Segoe UI", 0, 12));

        resetButton = new JButton("Reset");
        resetButton.setBounds(425,300,100,40);
        resetButton.addActionListener(this);
        resetButton.setFont(new java.awt.Font("Segoe UI", 0, 12));

        message = new JLabel();
        message.setBounds(300,350,300,40);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        // this.add(showPassword);
        this.add(button);
        this.add(resetButton);
        this.add(message);

        this.setVisible(true);
    }

    /* For any events (none yet) */
    @Override
    public void actionPerformed(ActionEvent event) {
        String message = "Login successful.";

    }



    /* Launch the application */
    public static void main(String[] args) {

        LoginPage frame = new LoginPage();
    }

}