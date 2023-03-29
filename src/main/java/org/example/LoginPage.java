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

/**
 * Login page that handles both user registration and login by storing data in /data/userData.json.
 * @author doh43
 */
public class LoginPage extends JFrame implements ActionListener {
    private JPasswordField password;
    private JTextField username;
    private JLabel passwordLabel, usernameLabel, message;
    private JButton button, registerButton;

    /** Displays the login page */
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

        /** Sign-in button that verifies username and password.
         *
         * @param ActionEvent
         * @throws exception
         * */
        button = new JButton(new AbstractAction("Sign in") {
            private static void dispose() {
            }
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

                /** Creates a new JSONObject with the user's inputted username and password and compares it with each object in the JSONArray. */
                JSONObject obj = new JSONObject();
                obj.put("Username", username.getText());
                obj.put("Password", password.getText());

                for (int i = 0; i < jArr.size(); i++) {
                    if (obj.equals(jArr.get(i))) {

                        this.dispose();
                        LandingPage frame = new LandingPage();

                    } else if (i == jArr.size()-1) {
                        JOptionPane.showMessageDialog(null, "Your username or password is incorrect.");
                    }
                }
            }
        });
        button.setBounds(300,300,100,40);
        button.addActionListener(this);
        button.setFont(new java.awt.Font("Segoe UI", 0, 12));

        /** Register button that saves new username and password.
         *
         * @param ActionEvent
         * @throws exception
         * */
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

    /** Launch the application */
    public static void main(String[] args) {

        LoginPage frame = new LoginPage();
    }
}