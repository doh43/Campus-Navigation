package org.example;

import javax.swing.*;
import java.awt.*;

public class FloorPanel extends JPanel {
    FloorPanel(){
        setLayout(new GridLayout());

        JButton floor1 = new JButton("1");
        JButton floor2 = new JButton("2");
        JButton floor3 = new JButton("3");
        JButton floor4 = new JButton("4");

        add(floor1);
        add(floor2);
        add(floor3);
        add(floor4);
    }
}
