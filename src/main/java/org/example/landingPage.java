package org.example;

import javax.swing.*;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class landingPage extends JFrame implements ActionListener {

    JComboBox campusBuildings;
    JLabel boxLabel;
    JButton help, back, about;

    landingPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,500);
        this.setTitle("Western Campus Navigation");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        String[] buildings = { "North Campus Building (NCB)", "Middlesex College (MC)", "Alumni Hall (AH)" };
        campusBuildings = new JComboBox(buildings);
        campusBuildings.setBounds(225,200,200,40);
        campusBuildings.addActionListener(this);

        boxLabel = new JLabel("Browse available campus buildings");
        boxLabel.setBounds(225,50,400,40);
        boxLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));
        boxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        help = new JButton("Help");
        help.setBounds(300,300,100,40);
        help.addActionListener(this);
        help.setFont(new java.awt.Font("Segoe UI", 0, 12));

        about = new JButton("About");
        about.setBounds(300,300,100,40);
        about.addActionListener(this);
        about.setFont(new java.awt.Font("Segoe UI", 0, 12));

        this.add(campusBuildings);
        this.add(boxLabel);
        this.add(about);
        this.add(help);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) {

        landingPage frame = new landingPage();
    }

}