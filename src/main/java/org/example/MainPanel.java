package org.example;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    MainPanel(BottomPanel botPan) {
        setLayout(new BorderLayout());

        add(new SearchPanel(), BorderLayout.NORTH);
        add(new MapPanel(), BorderLayout.CENTER);
        add(botPan, BorderLayout.SOUTH);

    }
}
