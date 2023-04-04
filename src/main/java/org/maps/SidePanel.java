
package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

/** Implements the side panel of the Map screen
 * @author Tomas Garcia, Ethan Tiger Wakefield
 * @version 1.0
 * @see org.maps.MainPanel
 * @see org.maps.PoiPanel */
public class SidePanel extends JLayeredPane {
    /** Displays existing POIs*/
    private static JPanel poiList;

    /** Displays the layers currently selected/deselected on the map */
    private static JPanel layer;

    /** Holds the checkboxes inside the layer panel */
    private static JPanel checkPan;

    /** Displays the current user's favourites */
    private static JPanel favourites;

    /** Holds all side panel components */
    private JPanel selection;

    /** Represent types of POIs */
    private JCheckBox cRoom, nav, wash, entry, genL, res, collab;

    /** Stores the POI names for each floor */
    private static JComboBox<String> poiDrop;

    /** Stores the POI IDs for each floor */
    private static int[] floorPoiIDs;
    /** Stores the favourites dropdown */
    private static JComboBox<String> favBox;

    /** SidePanel()
     * Constructor for the SidePanel class
     * Sets up the side panel of the map screen
     * @see org.maps.MainPanel
     * @see org.maps.PoiPanel */
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
        favBox = makeFavouritesDropdown();

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
    /** Rerenders the favourites dropdown */
    public static void updateFavourites() {
        String[] favNames = makeFavNameList();
        favBox.setModel(new DefaultComboBoxModel<>(favNames));
    }
    /** Creates a JComboBox with the favourited poi names
     * @return JComboBox<String> */
    public static JComboBox<String> makeFavouritesDropdown() {
        Data d = Data.getInstance();
        String[] favNames = makeFavNameList();
        JComboBox<String> favBox = new JComboBox<>(favNames);
        favBox.addActionListener(e -> {
            String selectedFav = (String) favBox.getSelectedItem();
            // find the poi and floor number that matches the selected favourite
            for (int i = 0; i < Maps.getMapBuilding().getFloors().length; i++) {
                JSONArray pois = Maps.getMapBuilding().getFloors()[i].getPois();
                for (int j = 0; j < pois.length(); j++) {
                    JSONObject poi = (JSONObject) pois.get(j);
                    if (poi.getString("name").equals(selectedFav)) {
                        // set the floor and poi
                        MapPanel.setFloorNum(i);
                        MapPanel.setUpTypePanels();
                        MapPanel.jumpToPoi(new Poi(poi));
                        return;
                    }
                }
            }
            // find the poi and floor number in custom pois that matches the selected favourite
            for (int i = 0; i < Maps.getMapBuilding().getFloors().length; i++) {
                JSONArray pois = d.getCustomPOIs(Maps.getBuildingCode(), i);
                for (int j = 0; j < pois.length(); j++) {
                    JSONObject poi = (JSONObject) pois.get(j);
                    if (poi.getString("name").equals(selectedFav)) {
                        // set the floor and poi
                        MapPanel.setFloorNum(i);
                        MapPanel.setUpTypePanels();
                        MapPanel.jumpToPoi(new Poi(poi));
                        return;
                    }
                }
            }


        });
        return favBox;
    }
    /** Creates a list of the names of the favourites
     * @return String[] of favourite names */
    public static String[] makeFavNameList() {
        Data d = Data.getInstance();
        JSONArray favs = d.getFavourites();
        String[] favNames = new String[favs.length()];
        // For all favourite IDs
        for (int i = 0; i < favs.length(); i++) {
            // for each poi in each floor in the building, check if the id matches the favourite id and add the name to the list
            for (int j = 0; j < Maps.getMapBuilding().getFloors().length; j++) {
                JSONArray pois = Maps.getMapBuilding().getFloors()[j].getPois();
                for (int k = 0; k < pois.length(); k++) {
                    JSONObject poi = (JSONObject) pois.get(k);
                    if (poi.getInt("id") == favs.getInt(i)) {
                        favNames[i] = poi.getString("name");
                    }
                }
            }
            // for each custom poi in each floor in the building, check if the id matches the favourite id and add the name to the list
            for (int j = 0; j < Maps.getMapBuilding().getFloors().length; j++) {
                JSONArray pois = d.getCustomPOIs(Maps.getBuildingCode(),j);
                for (int k = 0; k < pois.length(); k++) {
                    JSONObject poi = (JSONObject) pois.get(k);
                    if (poi.getInt("id") == favs.getInt(i)) {
                        favNames[i] = poi.getString("name");
                    }
                }
            }


        }
        int numFavs = 0;
        for (String favName : favNames) {
            if (favName != null) {
                numFavs++;
            }
        }
        String[] newFavNames = new String[numFavs];
        for (String favName: favNames) {
            if (favName != null) {
                newFavNames[numFavs - 1] = favName;
                numFavs--;
            }
        }



        return newFavNames;
    }

    /**
     * This method is responsible for retrieving all the POIs on a floor and storing it in a list.
     *
     * @return the POI names for the dropdown list
     */
    private static String[] makePoiNameList() {
        Data data = new Data();

        String building = Maps.getBuildingCode();
        int floorNum = MapPanel.getFloorNum();
        JSONArray customPois = data.getCustomPOIs(building, floorNum);
        JSONArray jsonPois = data.getPois(building, floorNum);
        JSONArray allPois = new JSONArray();
        allPois.putAll(jsonPois);
        allPois.putAll(customPois);
        int numPois = allPois.length();

        String[] poiNames = new String[numPois];
        floorPoiIDs = new int[numPois];

        for (int i = 0; i < numPois; i++) {
            JSONObject poi = (JSONObject) allPois.get(i);
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
        Data data = new Data();

        String[] poiNames = makePoiNameList();
        JComboBox<String> cb = new JComboBox<>(poiNames);
        cb.addActionListener(e -> {
                    // Retrieves whatever the user selected from the POI dropdown list
                    Object selection = poiDrop.getSelectedItem();

                    String building = Maps.getBuildingCode();
                    int floorNum = MapPanel.getFloorNum();
                    JSONArray customPois = data.getCustomPOIs(building, floorNum);
                    JSONArray jsonPois = data.getPois(building, floorNum);
                    JSONArray allPois = new JSONArray();
                    allPois.putAll(jsonPois);
                    allPois.putAll(customPois);
                    int numPois = allPois.length();

                    // Creates variable
                    String[] poiName = new String[numPois];

                    // Loops through the JSON file to find the JSONObject to create a pop-up POI
                    for (int i = 0; i < allPois.length(); i++) {
                        JSONObject poi = (JSONObject) allPois.get(i);
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
