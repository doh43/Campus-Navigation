package org.example;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private static JScrollPane mapScroll;

    MapPanel() {
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("./data/maps/ah/ah1.png"));
        mapScroll = new JScrollPane(imageLabel);

        add(mapScroll);
    }
    public static JScrollPane getMapScroll() {
        return mapScroll;
    }
}
