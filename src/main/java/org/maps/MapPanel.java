package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
/**
 * A panel that displays a map image and allows users to interact with points of interest (POIs) on the map.
 *
 * @version 1.0
 * @author Ethan Wakefield, Aryan Saxena
 * */
public class MapPanel extends JPanel {
    // STATIC VARIABLES
    /**
     * A static JPanel that is used to access the map panel.
     * */
    private static JPanel staticMapPanel;
    /**
     * A static JScrollPane that is used to access the map scroll pane.
     * */
    private static JScrollPane mapScroll;
    /**
     * A static int that is used to access the current floor number.
     * */
    private static int floorNum;
    /**
     * A static JLabel that is used to access the map image.
     * */
    private static JLabel imageLabel;
    /**
     * A static String that is used to access the building code.
     * */
    static String buildingCode;
    /**
     * A static JLayeredPane that is used to access the layered pane.
     * */
    private static JLayeredPane layeredPane;
    /**
     * A static HashMap that is used to access the type panels.
     * */
    private static HashMap<String, Integer> typePanels;
    /**
     * A static HashMap that is used to access the buttons.
     * */
    private static HashMap<Integer, JButton> allButtons;
    /**
     * Creates a new MapPanel object.
     * */
    MapPanel() {
        staticMapPanel = this;
        buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());

        floorNum = 0;

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
        SidePanel.updateDropDown();
    }

    public static int getFloorNum() {
        return floorNum;
    }

    /**
     * Jumps to a specific POI on the map.
     * @param id The unique ID of the POI to jump to.
     * */
    public static void jumpToPoi(int id){

        if (allButtons.containsKey(id)) {

            JButton targetButton = allButtons.get(id);
            Rectangle buttonBounds = targetButton.getBounds();
            JViewport targetViewport = mapScroll.getViewport();
            targetViewport.scrollRectToVisible(buttonBounds);
        }
    }

    /**
     * Toggles a specific POI type off.
     * @param type The type of POI to toggle off.
     * */
    public static void toggleLayerOff(String type){

        if (typePanels.containsKey(type)) {

            Integer targetLayerValue = typePanels.get(type);
            JPanel targetPanel = (JPanel) layeredPane.getComponentsInLayer(targetLayerValue)[0];
            targetPanel.setVisible(false);
        }
    }

    /**
     * Toggles a specific POI type on.
     * @param type The type of POI to toggle on.
     * */
    public static void toggleLayerOn(String type){

        if (typePanels.containsKey(type)) {

            Integer targetLayerValue = typePanels.get(type);
            JPanel targetPanel = (JPanel) layeredPane.getComponentsInLayer(targetLayerValue)[0];
            targetPanel.setVisible(true);
        }
    }


    /* METHOD # - setUpTypePanels()
     *     BottomPanel use:     - based on floor selection set up new display for image and type-layered-buttons
     *     Static Variables:
     *          imageLabel      - to load new map png
     *          layeredPane     - to load new type-layers and buttons
     *          typePanels      - store HashMap with (type, layer in layeredPane) K,V pairing
     *                              - to be used by METHOD # toggleLayer()
     *          allButtons      - store HashMap with (id, JButton object in layeredPane) K,V pairing
     *                              - to be used by METHOD # jumpToPoi()
     */

    /**
     * Sets up the map and points of interest (POIs) on the map.
     * */
    public static void setUpTypePanels() {

        // SETUP FLOOR IMAGE
        imageLabel.setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+floorNum+".png"));
        imageLabel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);


        // SETUP NEW layeredPane
        layeredPane = new JLayeredPane();
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setPreferredSize(imageLabel.getPreferredSize());


        // COLLECT POIs
        Data d = Data.getInstance();
        JSONArray allPois = new JSONArray(); // stores all POIs including both built-in and user-added
        JSONArray floorPois = d.getPois(buildingCode, floorNum);
        JSONArray userFloorPois = d.getCustomPOIs(buildingCode, floorNum);
        // MERGES USER-ADDED POIs WITH EXISTING POIs
        allPois.putAll(floorPois);
        allPois.putAll(userFloorPois);



        // SETUP STATIC VARIABLES
        typePanels = new HashMap<>();   // for toggleLayer()
        allButtons = new HashMap<>();   // for jumpToPoi()


        int layerDepth = 1;     // starting layer depth for type-panels


        // FOR ALL POIs IN SELECTED FLOOR
        for (Object obj : allPois) {
            JSONObject poi = (JSONObject) obj;

            String type = poi.getString("type");

            // CREATE TYPE-PANEL IF NEED BE
            if (!typePanels.containsKey(type)) {
                JPanel createPanel = new JPanel(null);
                createPanel.setBounds(0,0,imageLabel.getPreferredSize().width,imageLabel.getPreferredSize().height);
                createPanel.setOpaque(false);

                // type-panel: add, register, increment
                layeredPane.add(createPanel, JLayeredPane.DRAG_LAYER);
                layeredPane.setLayer(createPanel, layerDepth);
                typePanels.put(type, layerDepth);
                layerDepth += 1;
            }

            // SETUP BUTTON
            int posX = poi.getInt("posX");
            int posY = poi.getInt("posY");

            JButton button = new JButton();
            button.setBounds(posX-20,posY-20,40,40);
            button.setBackground(Color.BLUE);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener(e -> {
                if (e.getSource() == button) {
//                    Poi p = new Poi(poi);
                    PoiPopup p = new PoiPopup(new Poi(poi));
                    p.setVisible(true);
                }
            });
            // button: add, register
            int accessLayerValue = typePanels.get(type);
            JPanel targetPanel = (JPanel) (layeredPane.getComponentsInLayer(accessLayerValue))[0];
            targetPanel.add(button);
            allButtons.put(poi.getInt("id"), button);  //register poi (name,button) into hashMap
        }

        // ADD TO SCROLL PANE DISPLAY
        mapScroll.setViewportView(layeredPane);
        staticMapPanel.add(mapScroll);
    }
}
