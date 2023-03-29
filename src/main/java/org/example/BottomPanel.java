package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener {
    JButton floor1;
    JButton floor2;
    JButton floor3;
    JButton floor4;
    int floor;
    String buildingCode;
    BottomPanel(JButton btn) {
        this.buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());
        floor = 1;
        // FloorPanel
        JPanel floorPanel = new JPanel();
        floorPanel.setLayout(new GridLayout());

        floor1 = new JButton("1");
        floor2 = new JButton("2");
        floor3 = new JButton("3");
        floor4 = new JButton("4");

        floor1.addActionListener(this);
        floor2.addActionListener(this);
        floor3.addActionListener(this);
        floor4.addActionListener(this);

        floorPanel.add(floor1);
        floorPanel.add(floor2);
        floorPanel.add(floor3);
        floorPanel.add(floor4);


        add(floorPanel, BorderLayout.WEST);
        add(btn, BorderLayout.EAST);
    }

    public void changeFloor(int i) {
        MapPanel.setFloorNum(i);
//        MapPanel.getImageLabel().setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+i+".png"));
        MapPanel.setUpTypePanels();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == floor1) {
            floor = 1;
            changeFloor(floor);
        } else if (e.getSource() == floor2) {
            floor = 2;
            changeFloor(floor);
        } else if (e.getSource() == floor3) {
            floor = 3;
            changeFloor(floor);
        } else if (e.getSource() == floor4) {
            floor = 4;
            changeFloor(floor);
        }
    }
}
