package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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
     * @param poi to jump to
     * */
    public static void jumpToPoi(Poi poi){
        int id = poi.getId();

        if (allButtons.containsKey(id)) {
            JButton targetButton = allButtons.get(id);
            Rectangle buttonBounds = targetButton.getBounds();
            JViewport targetViewport = MapPanel.getMapScroll().getViewport();

            // Convert the button bounds to the viewport's coordinate space
            Point buttonLocation = SwingUtilities.convertPoint(targetButton.getParent(), buttonBounds.getLocation(), targetViewport);
            buttonBounds.setLocation(buttonLocation);

            // SIZE ADJUSTMENT
            buttonBounds.grow(400,400);

            targetViewport.scrollRectToVisible(buttonBounds);

            PoiPopup p = new PoiPopup(poi, targetButton);
            p.setLocationRelativeTo(targetButton);
            p.setVisible(true);

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
        JSONArray floorPois = d.getPois(buildingCode, floorNum);
        JSONArray userFloorPois = d.getCustomPOIs(buildingCode, floorNum);
        // MERGES USER-ADDED POIs WITH EXISTING POIs
        floorPois.putAll(userFloorPois);


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
            setButtonColor(button, type);   // SET BUTTON COLOR BASED ON TYPE

            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener(e -> {
                if (e.getSource() == button) {

                    PoiPopup p = new PoiPopup(new Poi(poi), button);
                    p.setLocationRelativeTo(button);
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

        /* IN THE WORKS **/
        JViewport tempViewport = mapScroll.getViewport();
        Dimension tempLayerSize = layeredPane.getPreferredSize();
        Dimension tempViewSize = tempViewport.getExtentSize();
        int x = (tempLayerSize.width - tempViewSize.width)/4;
        int y = (tempLayerSize.height - tempViewSize.height)/4;
        Rectangle viewRect = new Rectangle(x, y, tempViewSize.width, tempViewSize.height);
        tempViewport.scrollRectToVisible(viewRect);

        staticMapPanel.add(mapScroll);
    }

    /**
     * decide button color based on layer-type before adding to layer
     * @param button - JButton being configured during for loop iteration in setUpTypePanels()
     * @param type - the layer type
     */
    public static void setButtonColor(JButton button, String type) {

        String temp = type.toLowerCase().substring(0,2);

        //classroom
        if(temp.equals("cl"))
            button.setBackground(new Color(52, 132, 240));
        //restaurant
        else if(temp.equals("re"))
            button.setBackground(new Color(178, 102,255));
        //lab
        else if(temp.equals("la"))
            button.setBackground(new Color(211, 144, 79));
        //washroom
        else if(temp.equals("wa"))
            button.setBackground(new Color(255, 130, 130));
        //entry/exit
        else if(temp.equals("en"))
            button.setBackground(new Color(90, 90, 90));
        //navigation
        else if(temp.equals("na"))
            button.setBackground(new Color(0, 204, 0));
        //collaborative room
        else if(temp.equals("co"))
            button.setBackground(new Color(246, 207, 101));

    }

}
