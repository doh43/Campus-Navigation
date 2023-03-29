package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MapPanel extends JPanel {
    private static JPanel staticMapPanel;
    private static JScrollPane mapScroll;
    private static int floorNum;
    private static JLabel imageLabel;
    static String buildingCode;

    private static JLayeredPane layeredPane;


    // maybe make static variables that holds type-panels depth integers
    private static HashMap<String, Integer> typePanels;
    private static HashMap<String, JButton> allButtons;

    MapPanel() {
        staticMapPanel = this;
        this.buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());

        floorNum = 1;

        imageLabel = new JLabel();
        mapScroll = new JScrollPane();
        setUpTypePanels();

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

    public static void setUpTypePanels() {

        // new floor image setup
        imageLabel.setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+floorNum+".png"));
        imageLabel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);


        // new LayeredPanel setup
        layeredPane = new JLayeredPane();
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(imageLabel.getPreferredSize());


        // grab all the POIS for selected floor
        Data d = Data.getInstance();
        JSONArray floorPois = d.getPois(buildingCode, floorNum);


        // for type-panel toggle and button scrollTo action
        typePanels = new HashMap<>();
        allButtons = new HashMap<>();


        int layerDepth = 1;    // starting layer depth for type-panels

        for (Object obj : floorPois) {

            JSONObject poi = (JSONObject) obj;

            String type = poi.getString("type");            // grab the type

            // CREATE TYPE-PANEL IF NEED BE
            if (!typePanels.containsKey(type)) {

                JPanel createPanel = new JPanel(null);
                createPanel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);
                createPanel.setOpaque(false);

                //register, add, increment
                layeredPane.add(createPanel, JLayeredPane.DRAG_LAYER);                    //add to layeredPanel
                layeredPane.setLayer(createPanel, layerDepth);   //set layerDepth
                typePanels.put(type, layerDepth);               //register (type,depth) into hashMap
                layerDepth += 1;                                    //increment/keep track for next createPanel to be added
            }


            // SETUP BUTTON
            int posX = poi.getInt("posX");                  // grab coordinates
            int posY = poi.getInt("posY");

            JButton button = new JButton("B");              // create button
            button.setBounds(posX-20,posY-20,40,40);          // should be [x=974,y=346]
            button.setBackground(Color.BLUE);
            /* add action listener */

            int accessLayerValue = typePanels.get(type);

            JPanel targetPanel = (JPanel) (layeredPane.getComponentsInLayer(accessLayerValue))[0];
            targetPanel.add(button);
            allButtons.put(poi.getString("name"), button);  //register poi (name,button) into hashMap

        }

        mapScroll.setViewportView(layeredPane);
        staticMapPanel.add(mapScroll);

    }
}
