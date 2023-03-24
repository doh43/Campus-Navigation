package org.example;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    SidePanel() {
        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(200,1000));

        add(new PoiListPanel());
        add(new LayerPanel());
        add(new FavouritesPanel());
        add(new AddPoiPanel());
    }
}
