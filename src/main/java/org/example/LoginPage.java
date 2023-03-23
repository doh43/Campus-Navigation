package org.example;
import javax.swing.*;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.parser.JSONParser;


public class LoginPage extends JFrame implements ActionListener {
    private JPasswordField password;
    private JTextField username;
    private JLabel passwordLabel, usernameLabel, message;
    private JButton button, registerButton;

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

        /* Verify user sign in */
        button = new JButton(new AbstractAction("Sign in") {
            public void actionPerformed(ActionEvent e) {
                JSONArray jArr = new JSONArray();
                Object object = null;
                JSONParser parser = new JSONParser();

                try {
                    FileReader file = new FileReader("./data/userData.json");
                    object = parser.parse(file);
                    jArr = (JSONArray) object;
                    file.close();

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "An error occurred while fetching data.");
                }

                JSONObject obj = new JSONObject();
                obj.put("Username", username.getText());
                obj.put("Password", password.getText());

                for (int i = 0; i < jArr.size(); i++) {
                    if (obj.equals(jArr.get(i))) {

                        this.dispose();
                        landingPage frame = new landingPage();

                    } else if (i == jArr.size()-1) {
                        JOptionPane.showMessageDialog(null, "Your username or password is incorrect.");
                    }
                }
            }
            private static void dispose() {
            }
        });
        button.setBounds(300,300,100,40);
        button.addActionListener(this);
        button.setFont(new java.awt.Font("Segoe UI", 0, 12));

        /* Register Function */
        registerButton = new JButton(new AbstractAction("Register") {
            public void actionPerformed(ActionEvent event) {
                JSONArray jArr = new JSONArray();
                JSONObject obj = new JSONObject();
                JSONParser jp = new JSONParser();

                try {
                    FileReader file = new FileReader("./data/UserData.json");
                    jArr =(JSONArray)jp.parse(file);
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"An error occurred.");
                }


                obj.put("Username", username.getText());
                obj.put("Password", password.getText());

                jArr.add(obj);

                try {
                    FileWriter file = new FileWriter("./data/userData.json");
                    file.write(jArr.toJSONString());
                    file.close();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "An error occurred.");
                }
                JOptionPane.showMessageDialog(null, "Successfully added user.");
            }
        });
        registerButton.setBounds(420,300,100,40);
        registerButton.addActionListener(this);
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 12));

        message = new JLabel();
        message.setBounds(300,350,300,40);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(button);
        this.add(registerButton);
        this.add(message);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /* Launch the application */
    public static void main(String[] args) {

        LoginPage frame = new LoginPage();
    }
}