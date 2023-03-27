package org.example;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    BottomPanel(JButton btn) {
        setLayout(new BorderLayout());

        // FloorPanel
        JPanel floorPanel = new JPanel();
        floorPanel.setLayout(new GridLayout());

        JButton floor1 = new JButton("1");
        JButton floor2 = new JButton("2");
        JButton floor3 = new JButton("3");
        JButton floor4 = new JButton("4");

        floorPanel.add(floor1);
        floorPanel.add(floor2);
        floorPanel.add(floor3);
        floorPanel.add(floor4);

        add(floorPanel, BorderLayout.WEST);
        add(btn, BorderLayout.EAST);
    }
}
