package org.example;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private static JScrollPane mapScroll;
    private static int floorNum;
    private static JLabel imageLabel;
    String buildingCode;

    MapPanel() {
        this.buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());
        floorNum = 1;
        JLayeredPane l = new JLayeredPane();
        imageLabel = new JLabel(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+floorNum+".png"));
        imageLabel.setBounds(0,0,4000,2000);
        l.add(imageLabel);
//        l.setComponentZOrder(imageLabel, 0);
        l.setPreferredSize(new Dimension(4000, 2000));

        JButton b = new JButton("poi");
        b.setBounds(600,1200,30,30);
        l.add(b);
        l.setComponentZOrder(b,0);

        mapScroll = new JScrollPane();
        mapScroll.setViewportView(l);

        add(mapScroll);
    }
    public static JScrollPane getMapScroll() {
        return mapScroll;
    }
    public static void setFloorNum(int i) {
        floorNum = i;
    }
    public static int getFloorNum() {
        return floorNum;
    }
    public static JLabel getImageLabel() {
        return imageLabel;
    }
}
