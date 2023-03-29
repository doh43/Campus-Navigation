package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;

/**
 * This is the landing page GUI.
 *
 * The user will be able to choose a map from a dropdown list.
 * Users can also discover the about/help section using the buttons.
 *
 * @author tha7
 */
public class LandingPage extends JFrame implements ActionListener {
    JComboBox campusBuildings;
    JLabel boxLabel;
    JButton help, back, about, openMap;
    String choice;

    /**
     * Generates all the buttons for the landing page and the dropdown list.
     */
    LandingPage() {
        choice = "ncb";
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setTitle("Western Campus Navigation - Landing Page");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);

        String[] buildings = {"North Campus Building", "Middlesex College", "Alumni Hall"};
        campusBuildings = new JComboBox(buildings);
        campusBuildings.setBounds(200, 180, 300, 45);
        campusBuildings.addActionListener(this);

        boxLabel = new JLabel("<html><b>Browse available campus buildings</b>");
        boxLabel.setBounds(135, 100, 600, 40);
        boxLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        help = new JButton("Help");
        help.setBounds(240, 320, 100, 40);
        help.addActionListener(this);
        help.setFont(new java.awt.Font("Segoe UI", 0, 12));

        about = new JButton("About");
        about.setBounds(340, 320, 100, 40);
        about.addActionListener(this);
        about.setFont(new java.awt.Font("Segoe UI", 0, 12));

        back = new JButton("Back");
        back.setBounds(290, 400, 100, 40);
        back.addActionListener(this);
        back.setFont(new java.awt.Font("Segoe UI", 0, 12));

        openMap = new JButton("View Map");
        openMap.setBounds(240, 250, 200, 40);
        openMap.addActionListener(this);
        openMap.setFont(new java.awt.Font("Segoe UI", 0, 12));

        this.add(campusBuildings);
        this.add(boxLabel);
        this.add(about);
        this.add(help);
        this.add(back);
        this.add(openMap);
        this.setVisible(true);

    }

    /**
     * This class will retrieve the selected map's data.
     * Displays an error if the building cannot be found.
     * @param campusBuildings
     */
    private void buildingSelection(JComboBox campusBuildings) {
        try {
            String string = campusBuildings.getSelectedItem().toString();
            if (string.equals("Middlesex College")) {
                choice = "mc";
            }
            if (string.equals("Alumni Hall")) {
                choice = "ah";
            }
            if (string.equals("North Campus Building")) {
                choice = "ncb";
            }
        }
        // Whenever a building cannot be found, it will print a message.
        catch (Exception e)  {
            System.out.print("Building cannot be found");
        }
    }

    /**
     * Generates a certain action depending on which button the user selects.
     *
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back) {
            this.dispose();
            LoginPage frame = new LoginPage(); // Takes the user back to the login page.
        }
        if (event.getSource() == help) {
            this.dispose();
            Help frame = new Help(); // Takes the user to the help page.
        }
        if (event.getSource() == about) {
            this.dispose();
            About frame = new About(); // Takes the user to the about page.
        }
        if (event.getSource() == campusBuildings) {
            buildingSelection(campusBuildings);
        }
        if (event.getSource() == openMap) {
            this.dispose();
            Maps newMap = new Maps(choice); // Opens a map page for users to view.
        }
    }
}

