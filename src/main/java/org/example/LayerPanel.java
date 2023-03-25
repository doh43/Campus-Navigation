package org.example;

import javax.swing.*;
import java.awt.*;

public class LayerPanel extends JPanel {
    LayerPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        JLabel layerSelect = new JLabel("Layers");

        JPanel checkPan = new JPanel(new GridLayout(7,1));

        JCheckBox cRoom = new JCheckBox("Classrooms",true);
        JCheckBox nav = new JCheckBox("Navigation",true);
        JCheckBox wash = new JCheckBox("Washrooms",true);
        JCheckBox entry = new JCheckBox("Entry/Exit",true);
        JCheckBox genL = new JCheckBox("Labs",true);
        JCheckBox res = new JCheckBox("Restaurants",true);
        JCheckBox collab = new JCheckBox("Collaborative Rooms",true);

        checkPan.add(cRoom);
        checkPan.add(nav);
        checkPan.add(wash);
        checkPan.add(entry);
        checkPan.add(genL);
        checkPan.add(res);
        checkPan.add(collab);

        add(layerSelect, BorderLayout.NORTH);
        add(checkPan, BorderLayout.CENTER);

    }
}
