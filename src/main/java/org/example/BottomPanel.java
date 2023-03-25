package org.example;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    BottomPanel(JButton btn) {
        setLayout(new BorderLayout());

        add(new FloorPanel(), BorderLayout.WEST);
        add(btn, BorderLayout.EAST);
    }
}
