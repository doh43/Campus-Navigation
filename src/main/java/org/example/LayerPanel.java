package org.example;

import javax.swing.*;
import java.awt.*;

public class LayerPanel extends JPanel {
    LayerPanel() {
        setLayout(new GridLayout(8,1));
        setBackground(Color.lightGray);

        JLabel layerSelect = new JLabel("Layers");

        JCheckBox cRoom = new JCheckBox("Classrooms",true);
        JCheckBox nav = new JCheckBox("Navigation",true);
        JCheckBox wash = new JCheckBox("Washrooms",true);
        JCheckBox entry = new JCheckBox("Entry/Exit",true);
        JCheckBox genL = new JCheckBox("Labs",true);
        JCheckBox res = new JCheckBox("Restaurants",true);
        JCheckBox collab = new JCheckBox("Collaborative Rooms",true);

        add(layerSelect);
        add(cRoom);
        add(nav);
        add(wash);
        add(entry);
        add(genL);
        add(res);
        add(collab);
    }
}
