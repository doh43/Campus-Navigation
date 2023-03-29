package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MapPanel extends JPanel {
    private static JScrollPane mapScroll;
    private static int floorNum = 1;
    private static JLabel imageLabel;
    static String buildingCode;

    private static JLayeredPane layeredPane;


    // maybe make static variables that holds type-panels depth integers
    private static HashMap<String, Integer> typePanels;
    private static HashMap<String, JButton> allButtons;

    MapPanel() {
        this.buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());
        floorNum = 1;

        setUpTypePanels();  //statically builds layeredPane


        // set up map scroll pane
//        mapScroll = new JScrollPane();
//        mapScroll.setViewportView(layeredPane);
        add(mapScroll);
    }


    public static JScrollPane getMapScroll() {
        return mapScroll;
    }
    public static void setFloorNum(int i) {

        floorNum = i;
        layeredPane=null;
        setUpTypePanels();      //set up new LayeredPanel everytime new floor is selected
    }
    public static int getFloorNum() {
        return floorNum;
    }
    public static JLabel getImageLabel() {
        return imageLabel;
    }

    private static void setUpTypePanels() {

        // new floor image setup
        imageLabel = new JLabel(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+floorNum+".png"));
        imageLabel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);


        // new LayeredPanel setup
        JLayeredPane newLayeredPane = new JLayeredPane();
        newLayeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        newLayeredPane.setPreferredSize(imageLabel.getPreferredSize());


        // grab all the POIS for selected floor
        Data d = Data.getInstance();
        JSONArray floorPois = d.getPois(buildingCode, floorNum);


        // for type-panel toggle and button scrollTo action
        HashMap<String, Integer> tempTypePanels = new HashMap<>();
        HashMap<String, JButton> tempAllButtons = new HashMap<>();

        int layerDepth = 1;    // starting layer depth for type-panels

        for (Object obj : floorPois) {

            JSONObject poi = (JSONObject) obj;

            String type = poi.getString("type");            // grab the type

            // CREATE TYPE-PANEL IF NEED BE
            if (!tempTypePanels.containsKey(type)) {

                JPanel createPanel = new JPanel(null);
                createPanel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);
                createPanel.setOpaque(false);

                //register, add, increment
                newLayeredPane.add(createPanel, JLayeredPane.DRAG_LAYER);                    //add to layeredPanel
                newLayeredPane.setLayer(createPanel, layerDepth);   //set layerDepth
                tempTypePanels.put(type, layerDepth);               //register (type,depth) into hashMap
                layerDepth += 1;                                    //increment/keep track for next createPanel to be added
            }


            // SETUP BUTTON
            int posX = poi.getInt("posX");                  // grab coordinates
            int posY = poi.getInt("posY");

            JButton button = new JButton("B");              // create button
            button.setBounds(posX,posY,40,40);          // should be [x=974,y=346]
            button.setBackground(Color.BLUE);
            /* b.addActionListener ( PopUp class ) */

            int accessLayerValue = tempTypePanels.get(type);

            JPanel targetPanel = (JPanel) (newLayeredPane.getComponentsInLayer(accessLayerValue))[0];
            targetPanel.add(button);
            tempAllButtons.put(poi.getString("name"), button);  //register poi (name,button) into hashMap

//            System.out.println("DEBUGGING ---- ");
//            int i=0;
//            Component[] checkComps = newLayeredPane.getComponents();
//            for (Component component : checkComps) {
//                System.out.println("Object: " + i + ", Layer: " + newLayeredPane.getLayer(component) + ", Index: " + newLayeredPane.getIndexOf(component));
//                i += 1;
//            }

        }

        layeredPane = newLayeredPane;
        typePanels = tempTypePanels;        // statically access and use setVisibility for type-toggling
        allButtons = tempAllButtons;        // statically access and use the scrollTo method to jump to coordinates
                                                // maybe even call pop up

        mapScroll = new JScrollPane();
        mapScroll.setViewportView(layeredPane);

    }
}
