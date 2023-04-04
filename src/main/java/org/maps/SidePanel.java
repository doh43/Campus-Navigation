/** Implements the side panel of the Map screen
 * @author Tomas Garcia, Ethan Tiger Wakefield
 * @version 1.0
 * @see org.maps.MainPanel
 * @see org.maps.PoiPanel */
package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SidePanel extends JLayeredPane {
    /* Displays existing POIs*/
    private static JPanel poiList;

    /* Displays the layers currently selected/deselected on the map */
    private static JPanel layer;

    /* Holds the checkboxes inside the layer panel */
    private static JPanel checkPan;

    /* Displays the current user's favourites */
    private static JPanel favourites;
    private static Object selectedPOI;

    /* Holds all side panel components */
    private JPanel selection;

    /* Represent types of POIs */
    private JCheckBox cRoom, nav, wash, entry, genL, res, collab;

    /* Stores the POI names for each floor */
    private static JComboBox<String> poiDrop;

    /* Stores the ids of the POIs stored in poiDrop */
    private static int[] floorPoiIDs;

    /** Creates a new side panel
     * Contains a PoiPanel that will hide the side panel components and expand when pressed
     * @ //TODO: 2023-03-29 Change the dropdown for favourites to access POI data */
    SidePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(200,1000));

        selection = new JPanel();
        selection.setBounds(0,0,200,600);

        // PoiListPanel
        poiList = new JPanel();
        poiList.setLayout(new BorderLayout());
        poiList.setBackground(Color.lightGray);
        poiList.setPreferredSize(new Dimension(200,50));

        /* Title for the poi list panel */
        JLabel poiSelect = new JLabel("Select POI:");

        /* Creates a JComboBox with an action listener */
        poiDrop = addPoiDropdown();

        poiList.add(poiSelect, BorderLayout.NORTH);
        poiList.add(poiDrop, BorderLayout.CENTER);

        // LayerPanel
        layer = new JPanel();
        layer.setLayout(new BorderLayout());
        layer.setBackground(Color.lightGray);
        layer.setPreferredSize(new Dimension(200,220));

        /* Title for the layers panel */
        JLabel layerSelect = new JLabel("Layers");

        checkPan = new JPanel(new GridLayout(7,1));

        cRoom = new JCheckBox("Classrooms",true);
        addLayerListener(cRoom);

        nav = new JCheckBox("Navigation",true);
        addLayerListener(nav);

        wash = new JCheckBox("Washrooms",true);
        addLayerListener(wash);

        entry = new JCheckBox("Entries / Exits",true);
        addLayerListener(entry);

        genL = new JCheckBox("Labs",true);
        addLayerListener(genL);

        res = new JCheckBox("Restaurants",true);
        addLayerListener(res);

        collab = new JCheckBox("Collaborative Rooms",true);
        addLayerListener(collab);

        checkPan.add(cRoom);
        checkPan.add(nav);
        checkPan.add(wash);
        checkPan.add(entry);
        checkPan.add(genL);
        checkPan.add(res);
        checkPan.add(collab);

        layer.add(layerSelect, BorderLayout.NORTH);
        layer.add(checkPan, BorderLayout.CENTER);

        // Favourites
        favourites = new JPanel();
        favourites.setLayout(new BorderLayout());
        favourites.setBackground(Color.lightGray);
        favourites.setPreferredSize(new Dimension(200,50));

        /* Title for the favourites panel */
        JLabel favSelect = new JLabel("Favourites");

        String[] favs = {"fav1","fav2","fav3","fav4"};
        JComboBox<String> favBox = new JComboBox<>(favs);

        favourites.add(favSelect, BorderLayout.NORTH);
        favourites.add(favBox, BorderLayout.CENTER);

        /* Empty panel used to add distance between other panels */
        JPanel emptyPan = new JPanel();
        emptyPan.setPreferredSize(new Dimension(200,130));

        selection.add(poiList);
        selection.add(emptyPan);
        selection.add(layer);
        selection.add(favourites);

        add(selection, Integer.valueOf(0));
        add(new PoiPanel(), Integer.valueOf(1));
    }

    /**
     * This method is responsible for retrieving all the POIs on a floor and storing it in a list.
     *
     * @return the POI names for the dropdown list
     */
    private static String[] makePoiNameList() {
        int floorNum = MapPanel.getFloorNum();
        JSONArray jsonPois = Maps.getMapBuilding().getFloors()[floorNum].getPois();
        int numPois = jsonPois.length();

        String[] poiNames = new String[numPois];
        floorPoiIDs = new int[numPois];

        for (int i = 0; i < numPois; i++) {
            JSONObject poi = (JSONObject) jsonPois.get(i);
            poiNames[i] = poi.getString("name");
            floorPoiIDs[i] = poi.getInt("id");
        }
        return poiNames;
    }

    /**
     * This method creates the dropdown list and makes the dropdown list functional whenever a user selects a POI from it.
     *
     * @return cb, the combo-box or the dropdown list
     */
    private static JComboBox<String> addPoiDropdown() {
        String[] poiNames = makePoiNameList();
        JComboBox<String> cb = new JComboBox<>(poiNames);
        cb.addActionListener(e -> {
            // Retrieves whatever the user selected from the POI dropdown list
            Object selection = poiDrop.getSelectedItem();

            // Retrieves the POI data
            int floorNum = MapPanel.getFloorNum();
            JSONArray jsonPois = Maps.getMapBuilding().getFloors()[floorNum].getPois();
            int numPois = jsonPois.length();

            // Creates variable
            String[] poiName = new String[numPois];

            // Loops through the JSON file to find the JSONObject to create a pop-up POI
            for (int i = 0; i < jsonPois.length(); i++) {
                JSONObject poi = (JSONObject) jsonPois.get(i);
                poiName[i] = poi.getString("name");

                // If the name of the POI is equivalent to what is in the JSON file, it creates a pop-up
                if (selection.toString().equals(poiName[i])) {

                   // Poi tempPoi = new Poi(poi);

                    MapPanel.jumpToPoi(new Poi(poi));
/*
                    PoiPopup p = new PoiPopup(tempPoi);
                    p.setLocation(tempPoi.getPosX(), tempPoi.getPosY());
                    p.setVisible(true); */
                }
            }
        }
        );
        return cb;
    }

    /**
     * Updates the dropdown list with new POIs.
     */
    public static void updateDropDown() {
        String[] poiNames = makePoiNameList();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>( poiNames );
        poiDrop.setModel(model);
    }

    /** Enables or disables a POI type layer from the map
     * Current state: Tracks whether any of the layer checkboxes are selected/deselected
     * @param check1 checkbox whose state is being tracked */
    private void addLayerListener(JCheckBox check1) {
        check1.addItemListener(e -> {
            if (e.getSource() == cRoom) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Classroom");
                }
                else {
                    MapPanel.toggleLayerOff("Classroom");
                }
            }
            else if (e.getSource() == nav) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Navigation");
                }
                else {
                    MapPanel.toggleLayerOff("Navigation");
                }
            }
            else if (e.getSource() == wash) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Washroom");
                }
                else {
                    MapPanel.toggleLayerOff("Washroom");
                }
            }
            else if (e.getSource() == entry) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Entry / Exit");
                }
                else {
                    MapPanel.toggleLayerOff("Entry / Exit");
                }
            }
            else if (e.getSource() == genL) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Lab");
                }
                else {
                    MapPanel.toggleLayerOff("Lab");
                }
            }
            else if (e.getSource() == res) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Restaurant");
                }
                else {
                    MapPanel.toggleLayerOff("Restaurant");
                }
            }
            else if (e.getSource() == collab) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    MapPanel.toggleLayerOn("Collaborative Room");
                }
                else {
                    MapPanel.toggleLayerOff("Collaborative Room");
                }
            }
        });
    }

    /** Disables the side panel components when the ADD poi button is pressed */
    public static void disableSelection() {
        poiList.getComponent(1).setEnabled(false);
        favourites.getComponent(1).setEnabled(false);
        for (Component c: checkPan.getComponents()) {
            if (c instanceof JCheckBox) {
                c.setEnabled(false);
            }
        }
    }

    /** Enables the side panel components when the ADD poi operation has been completed/cancelled
     * @see PoiPanel */
    public static void enableSelection() {
        poiList.getComponent(1).setEnabled(true);
        favourites.getComponent(1).setEnabled(true);
        for (Component c: checkPan.getComponents()) {
            if (c instanceof JCheckBox) {
                c.setEnabled(true);
            }
        }
    }
}
