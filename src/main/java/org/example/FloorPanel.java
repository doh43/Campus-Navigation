package org.example;

import javax.swing.*;
import java.awt.*;

public class FloorPanel extends JPanel {
    FloorPanel(){
        setLayout(new GridLayout());

        JToggleButton floor1 = new JToggleButton("1",true);
        JToggleButton floor2 = new JToggleButton("2");
        JToggleButton floor3 = new JToggleButton("3");
        JToggleButton floor4 = new JToggleButton("4");

        add(floor1);
        add(floor2);
        add(floor3);
        add(floor4);
    }
}
