package org.example;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static JPanel map;

    MainPanel(BottomPanel botPan, String buildingCode) {
        setLayout(new BorderLayout());

        map = new MapPanel(buildingCode);

        add(new SearchPanel(), BorderLayout.NORTH);
        add(map, BorderLayout.CENTER);
        add(botPan, BorderLayout.SOUTH);

    }
}
