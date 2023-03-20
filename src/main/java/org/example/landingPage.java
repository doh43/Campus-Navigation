package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Component;

public class landingPage extends JFrame implements ActionListener {
    JComboBox campusBuildings;
    JLabel boxLabel;
    JButton help, back, about;
    JPanel middle;
    landingPage() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setTitle("Western Campus Navigation");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        String[] buildings = {"North Campus Building (NCB)", "Middlesex College (MC)", "Alumni Hall (AH)"};
        campusBuildings = new JComboBox(buildings);
        campusBuildings.setBounds(240, 180, 200, 40);
//        campusBuildings.addActionListener(this);

        boxLabel = new JLabel("<html><b>Browse available campus buildings</b>");
        boxLabel.setBounds(135, 100, 600, 40);
        boxLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        help = new JButton("Help");
        help.setBounds(240, 280, 100, 40);
        help.addActionListener(this);
        help.setFont(new java.awt.Font("Segoe UI", 0, 12));

        about = new JButton("About");
        about.setBounds(340, 280, 100, 40);
        about.addActionListener(this);
        about.setFont(new java.awt.Font("Segoe UI", 0, 12));

        back = new JButton("Back");
        back.setBounds(290, 400, 100, 40);
        back.addActionListener(this);
        back.setFont(new java.awt.Font("Segoe UI", 0, 12));

        this.add(campusBuildings);
        this.add(boxLabel);
        this.add(about);
        this.add(help);
        this.add(back);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back) {
            this.dispose();
            LoginPage frame = new LoginPage();

        }

        if (event.getSource() == help) {
            this.dispose();
            help frame = new help();
        }

        if (event.getSource() == about) {
            this.dispose();
            about frame = new about();
        }
    }
}

