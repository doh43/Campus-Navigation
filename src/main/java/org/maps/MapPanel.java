package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MapPanel extends JPanel {

    // STATIC VARIABLES
    private static JPanel staticMapPanel;
    private static JScrollPane mapScroll;
    private static int floorNum;
    private static JLabel imageLabel;
    static String buildingCode;
    private static JLayeredPane layeredPane;
    private static HashMap<String, Integer> typePanels;
    private static HashMap<String, JButton> allButtons;

    /** CONSTRUCTOR: MapPanel()
     *
     */
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


    public static JLabel getImageLabel() {                      //is this being used ????
        return imageLabel;
    }

    /** METHOD 1: jumpToPoi(id)
     *
     * @param id - unique id of poi
     */
    public static void jumpToPoi(String id){

        if (allButtons.containsKey(id)) {

            JButton targetButton = allButtons.get(id);
            Rectangle buttonBounds = targetButton.getBounds();
            JViewport targetViewport = mapScroll.getViewport();
            targetViewport.scrollRectToVisible(buttonBounds);
        }
    }

    /** METHOD 2: toggleLayerOff(type)
     *
     * @param type - string variable for poi-types to be toggled off
     */
    public static void toggleLayerOff(String type){

        if (typePanels.containsKey(type)) {

            Integer targetLayerValue = typePanels.get(type);
            JPanel targetPanel = (JPanel) layeredPane.getComponentsInLayer(targetLayerValue)[0];
            targetPanel.setVisible(false);
        }
    }

    /** METHOD 3: toggleLayerOn(type)
     *
     * @param type - string variable for poi-types to be toggled on
     */
    public static void toggleLayerOn(String type){

        if (typePanels.containsKey(type)) {

            Integer targetLayerValue = typePanels.get(type);
            JPanel targetPanel = (JPanel) layeredPane.getComponentsInLayer(targetLayerValue)[0];
            targetPanel.setVisible(true);
        }
    }


    /** METHOD # - setUpTypePanels()
     *     BottomPanel use:     - based on floor selection set up new display for image and type-layered-buttons
     *     Static Variables:
     *          imageLabel      - to load new map png
     *          layeredPane     - to load new type-layers and buttons
     *          typePanels      - store HashMap with (type, layer in layeredPane) K,V pairing
     *                              - to be used by METHOD # toggleLayer()
     *          allButtons      - store HashMap with (id, JButton object in layeredPane) K,V pairing
     *                              - to be used by METHOD # jumpToPoi()
     */
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
        JSONArray floorPois = d.getPois(buildingCode, floorNum);
        JSONArray userFloorPois = d.getCustomPOIs(buildingCode, floorNum);
        floorPois.putAll(userFloorPois);
        // TODO: Add custom POIs here!!!

        // SETUP STATIC VARIABLES
        typePanels = new HashMap<>();   // for toggleLayer()
        allButtons = new HashMap<>();   // for jumpToPoi()


        int layerDepth = 1;     // starting layer depth for type-panels


        // FOR ALL POIs IN SELECTED FLOOR
        for (Object obj : floorPois) {
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
            allButtons.put(poi.getString("name"), button);  //register poi (name,button) into hashMap
        }

        // ADD TO SCROLL PANE DISPLAY
        mapScroll.setViewportView(layeredPane);
        staticMapPanel.add(mapScroll);
    }
}
