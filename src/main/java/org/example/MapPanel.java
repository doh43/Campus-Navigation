package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private static JScrollPane mapScroll;
    private static int floorNum;
    private static JLabel imageLabel;
    String buildingCode;

    private JLayeredPane layeredPane;

    MapPanel() {
        this.buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());
        floorNum = 1;


        // grab map image
        imageLabel = new JLabel(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+floorNum+".png"));
        imageLabel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);


        // set up layeredPane
        layeredPane = new JLayeredPane();
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(imageLabel.getPreferredSize());


        /* FUNCTION FOR SETTING UP THE TYPE-PANELS */
        setUpPanels();


        // test button
//        JButton b = new JButton("B");
//        b.setBounds(80,80,40,40);
//        b.setBackground(Color.BLUE);


        // test panel for poi-type setup
//        JPanel washroomPanel = new JPanel(null);
//        washroomPanel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);
//        //washroomPanel.setPreferredSize(imageLabel.getPreferredSize());
//        washroomPanel.setOpaque(false);
//        washroomPanel.add(b);


        // add type-panel components to layered pane
//        layeredPane.add(washroomPanel, JLayeredPane.DRAG_LAYER);
        //layeredPane.setComponentZOrder(b,0);


        // set up map scroll pane
        mapScroll = new JScrollPane();
        mapScroll.setViewportView(layeredPane);

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

    private void setUpPanels() {

        Data d = Data.getInstance();
        JSONArray floorPois = d.getPois(buildingCode, floorNum);

        JPanel washroomPanel = new JPanel(null);
        washroomPanel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);
        washroomPanel.setOpaque(false);


        for (Object obj : floorPois) {

            JSONObject poi = (JSONObject) obj;
            int posX = poi.getInt("posX");
            int posY = poi.getInt("posY");


            JButton b = new JButton("B");
            b.setBounds(posX,posY,40,40);   //[x=974,y=346]
            b.setBackground(Color.BLUE);

            washroomPanel.add(b);

        }

        layeredPane.add(washroomPanel, JLayeredPane.DRAG_LAYER);
    }
}
