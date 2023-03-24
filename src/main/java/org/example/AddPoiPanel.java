package org.example;

import javax.swing.*;
import java.awt.*;

public class AddPoiPanel extends JPanel {
    AddPoiPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        JLabel addSelect = new JLabel("Add POI");
        JButton poiAdd = new JButton("+");

        add(addSelect, BorderLayout.NORTH);
        add(poiAdd, BorderLayout.CENTER);
    }
}
