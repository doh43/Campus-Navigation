package org.maps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    JButton back;

    About() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setTitle("Western Campus Navigation - About");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        back = new JButton("Back");
        back.setBounds(290, 400, 100, 40);
        back.addActionListener(this);
        back.setFont(new java.awt.Font("Segoe UI", 0, 12));

        String text = "<html> <h1>About</h1>" +
                "<p>Version 1.12</p>" +
                "<p>Released April 5th, 2023</p>" +
                "<p>Created by:<br>" +
                "Aryan Saxena: asaxen8@uwo.ca <br>" +
                "Tomas Garcia: tgarci3@uwo.ca <br>" +
                "Daniel Oh: doh43@uwo.ca <br>" +
                "Taejun Ha: tha73@uwo.ca <br>" +
                "Ethan Tiger Wakefield: ewakefi@uwo.ca</p></html>";

        JTextPane aboutText = new JTextPane();
        aboutText.setContentType("text/html");
        aboutText.setText(text);
        aboutText.setEditable(false);
        aboutText.setBounds(5, 5, 600, 380);
        aboutText.setFont(new java.awt.Font("Segoe UI", 0, 12));
        aboutText.setBackground(this.getBackground());

        this.add(aboutText);
        this.add(back);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back) {
            this.dispose();
            LandingPage frame = new LandingPage();

        }
    }
}