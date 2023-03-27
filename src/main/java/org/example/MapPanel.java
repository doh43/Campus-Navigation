package org.example;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    MapPanel() {
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("./data/maps/ah/ah1.png"));
        JScrollPane mapScroll = new JScrollPane(imageLabel);

        add(mapScroll);
    }
}
