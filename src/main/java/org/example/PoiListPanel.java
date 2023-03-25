package org.example;

import javax.swing.*;
import java.awt.*;

public class PoiListPanel extends JPanel {
    PoiListPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        JLabel poiSelect = new JLabel("Select POI:");
        String[] pois = {"Classroom1","Rest2","Lab3","Stair"};
        JComboBox<String> cb = new JComboBox<>(pois);

        add(poiSelect, BorderLayout.NORTH);
        add(cb, BorderLayout.CENTER);
    }
}
