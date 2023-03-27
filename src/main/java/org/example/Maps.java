package org.example;
import javax.swing.*;
import java.awt.*;

public class Maps extends JFrame {
    Maps(String buildingCode) {
        setTitle("Western Campus Navigation - Map Page");
        setSize(1400,820);
        setLayout(new BorderLayout());


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Must be in Maps because of the dispose method
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            if (e.getSource() == back) {
                dispose();
                new LandingPage();

            }
        });

        BottomPanel botPan = new BottomPanel(back, buildingCode);

        add(new SidePanel(), BorderLayout.WEST);
        add(new MainPanel(botPan, buildingCode), BorderLayout.CENTER);
//        pack();
        setVisible(true);

    }

    public static void main(String[] args) {

        Maps frame = new Maps("mc");
    }
}
