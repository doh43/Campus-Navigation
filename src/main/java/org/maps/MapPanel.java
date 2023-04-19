package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


/** A panel that displays a map image and allows users to interact with points of interest (POIs) on the map.
 * @author Ethan Wakefield, Aryan Saxena
 * @version 1.0 */
public class MapPanel extends JPanel {
    // STATIC VARIABLES
    /** A static JPanel that is used to access the map panel. */
    private static JPanel staticMapPanel;

    /** A static JScrollPane that is used to access the map scroll pane. */
    private static JScrollPane mapScroll;

    /** A static int that is used to access the current floor number. */
    private static int floorNum;

    /** A static JLabel that is used to access the map image. */
    private static JLabel imageLabel;

    /** A static String that is used to access the building code. */
    static String buildingCode;

    /** A static JLayeredPane that is used to access the layered pane. */
    private static JLayeredPane layeredPane;

    /** A static HashMap that is used to access the type panels. */
    private static HashMap<String, Integer> typePanels;

    /** A static HashMap that is used to access the buttons. */
    private static HashMap<Integer, JButton> allButtons;


    private static HashMap<JButton, String> typeButtons = new HashMap<>();            // (JButton, String)

    private static HashMap<String, String> toggleState = new HashMap<>();


    private static HashMap<Integer, List<Object>> allToggleButtons = new HashMap<>();
        // (id, [type, fav, builtIn, JButton] )
        // (x, ["xx", true, true, JButton object] )



    /** Creates a new MapPanel object. */
    MapPanel() {
        staticMapPanel = this;
        buildingCode = Maps.getBuildingCode();
        setLayout(new BorderLayout());

        floorNum = 0;

        imageLabel = new JLabel();
        mapScroll = new JScrollPane();
        setUpTypePanels();

//        add(mapScroll);
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

    /** Jumps to a specific POI on the map.
     * @param poi poi to jump to */
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
            p.setLocationRelativeTo(mapScroll);
            p.setVisible(true);

        }
    }

    /** Toggles a specific POI type on.
     * @param name The type of POI to toggle off. */
    public static void toggleON(String name){

        String temp = name.toLowerCase().substring(0,2);
        toggleState.put(temp,"showing");

        //FAVORITES
        if(temp.equals("fa")) {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {

                // SKIP - skip if type is "hidden"
                if (toggleState.get((String)entry.getValue().toArray()[0]).equals("hidden"))
                    continue;

                // SKIP - skip if builtIn "us" is "hidden"
                else if ( !(Boolean)entry.getValue().toArray()[3] && toggleState.get("us").equals("hidden"))
                    continue;

                else
                    ((JButton)entry.getValue().toArray()[1]).setVisible(true);

            }
        }

        //USER CREATED
        else if(temp.equals("us")) {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {

                // SKIP - if type is "hidden"
                if (toggleState.get((String)entry.getValue().toArray()[0]).equals("hidden"))
                    continue;

                // SKIP - if favorite is "hidden"
                else if ( (Boolean)entry.getValue().toArray()[2] && toggleState.get("fa").equals("hidden"))
                    continue;

                else
                    ((JButton)entry.getValue().toArray()[1]).setVisible(true);
            }
        }

        // TYPES
        else {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {

                // SKIP - if favorite is "hidden"
                if ( (Boolean)entry.getValue().toArray()[2] && toggleState.get("fa").equals("hidden"))
                    continue;

                // SKIP - if user-created is "hidden"
                else if ( !(Boolean)entry.getValue().toArray()[3] && toggleState.get("us").equals("hidden"))
                    continue;

                // SHOW if type matches
                else if ( ((String)(entry.getValue().toArray()[0])).equals(temp) )
                    ((JButton)entry.getValue().toArray()[1]).setVisible(true);
            }
        }
    }

    /** Toggles a specific POI type off.
     * @param name type of poi to toggle. */
    public static void toggleOFF(String name){
        String temp = name.toLowerCase().substring(0,2);
        toggleState.put(temp,"hidden");

        //FAVORITES
        if(temp.equals("fa")) {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {


               if ( (Boolean)entry.getValue().toArray()[2] ) {

                   ((JButton)entry.getValue().toArray()[1]).setVisible(false);
               }

            }
        }

        //USER CREATED
        else if(temp.equals("us")) {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {

                if ( !((Boolean)entry.getValue().toArray()[3]) )

                    ((JButton)entry.getValue().toArray()[1]).setVisible(false);
            }
        }

        // TYPES
        else {
            for (Map.Entry<Integer, List<Object>> entry : allToggleButtons.entrySet()) {

                if (entry.getValue().toArray()[0].equals(temp))

                    ((JButton)entry.getValue().toArray()[1]).setVisible(false);
            }
        }
    }


    /** Sets up the map and points of interest (POIs) on the map. */
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

        //TOGGLE STUFF
        JSONArray userFavs = d.getFavourites();

        toggleState.put("cl","showing");
        toggleState.put("na","showing");
        toggleState.put("wa","showing");
        toggleState.put("en","showing");
        toggleState.put("la","showing");
        toggleState.put("re","showing");
        toggleState.put("co","showing");
        toggleState.put("us","showing");
        toggleState.put("fa","showing");


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

            // BUILD USER-CREATED and FAVORITES TOGGLE HASHMAP
            if(poi.getBoolean("favourited")){

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


            // LOAD BY TYPES
                // tempList -> ["ca", JButton, favourited, builtin]

            List<Object> tempList = new ArrayList<>();

            tempList.add(type.toLowerCase().substring(0,2));            // ["ca",
            tempList.add(button);                                       // JButton,

            tempList.add(false);                                        // [2] favourited - false,

            // CHANGE FAVORITES
            for (int i=0; i < userFavs.length(); i++){

                if ( userFavs.getInt(i) == poi.getInt("id") )       // change to true if it's a favorite
                    tempList.set(2,true);
            }

            // LOAD USER-CREATED
            tempList.add(true);                                        // [3] true - built in
            if(!poi.getBoolean("builtIn"))                         //       false - user-created
                tempList.set(3, false);

            allToggleButtons.put(poi.getInt("id"), tempList);
        }

        // ADD TO SCROLL PANE DISPLAY
        mapScroll.setViewportView(layeredPane);
        staticMapPanel.add(mapScroll);
    }

    /** Decides button color based on layer-type before adding to layer
     * @param button - JButton being configured during for loop iteration in setUpTypePanels()
     * @param type - the layer type */
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
