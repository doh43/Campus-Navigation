package org.example;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.parser.JSONParser;


public class LoginPage extends JFrame implements ActionListener {
    JPasswordField password;
    JTextField username;
    JLabel passwordLabel, usernameLabel, message, title;
    JButton button, resetButton, registerButton;
    JCheckBox showPassword;

    JSONArray arr = new JSONArray();

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

        /*
        resetButton = new JButton("Reset");
        resetButton.setBounds(425,300,100,40);
        // resetButton.addActionListener(this);
        resetButton.setFont(new java.awt.Font("Segoe UI", 0, 12));
        */
        registerButton = new JButton("Register");
        registerButton.setBounds(420,300,100,40);
        registerButton.addActionListener(this);
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 12));

        message = new JLabel();
        message.setBounds(300,350,300,40);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        // this.add(showPassword);
        this.add(button);
        //this.add(resetButton);
        this.add(registerButton);
        this.add(message);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        JSONArray jArr = new JSONArray();
        Object object = null;
        JSONParser parser = new JSONParser();

        if (event.getSource() == button) {
            try {
                FileReader file = new FileReader("./data/userData.json");
                object = parser.parse(file);
                jArr = (JSONArray) object;
                file.close();
            } catch(Exception poop) {
                JOptionPane.showMessageDialog(null, "Error occurred while fetching!");
            }
            this.dispose();
            landingPage frame = new landingPage();
        }

        JSONObject obj = new JSONObject();

        if (event.getSource() == registerButton) {

            obj.put("Username", username.getText());
            obj.put("Password", password.getText());

            arr.add(obj);

            JOptionPane.showMessageDialog(null, arr);

            try {
                FileWriter file = new FileWriter("./data/userData.json");
                file.write(arr.toJSONString());
                file.close();
            } catch(Exception poop) {
                JOptionPane.showMessageDialog(null, "An error occurred.");
            }
        }

    }



    /* Launch the application */
    public static void main(String[] args) {

        LoginPage frame = new LoginPage();
    }

}