package org.example;
import javax.swing.*;
import java.awt.*;

public class Maps extends JFrame {
    Maps() {
        setTitle("Western Campus Navigation - Map Page");
        setLayout(new BorderLayout());
        setSize(1400,1000);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Must be in Maps because of the dispose method
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            if (e.getSource() == back) {
                dispose();
                new LandingPage();

            }
        });

        BottomPanel botPan = new BottomPanel(back);

        add(new SidePanel(), BorderLayout.WEST);
        add(new MainPanel(botPan), BorderLayout.CENTER);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {

        Maps frame = new Maps();
    }
}
