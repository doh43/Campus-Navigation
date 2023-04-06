package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

/** Implements the side panel of the Map screen
 * @author Tomas Garcia, Ethan Tiger Wakefield, Daniel Oh
 * @version 3.0
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
    private static JToggleButton cRoom, nav, wash, entry, genL, res, collab, user, favs;

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
        layer.setPreferredSize(new Dimension(200,245));

        /* Title for the layers panel */
        JLabel layerSelect = new JLabel("Layers");

        checkPan = new JPanel(new GridLayout(9,1));

        cRoom = new JToggleButton("Classrooms");
        cRoom.setSelected(true);
        addLayerChangeTracker(cRoom);

        nav = new JToggleButton("Navigation");
        nav.setSelected(true);
        addLayerChangeTracker(nav);

        wash = new JToggleButton("Washrooms");
        wash.setSelected(true);
        addLayerChangeTracker(wash);

        entry = new JToggleButton("Entries / Exits");
        entry.setSelected(true);
        addLayerChangeTracker(entry);

        genL = new JToggleButton("Labs");
        genL.setSelected(true);
        addLayerChangeTracker(genL);

        res = new JToggleButton("Restaurants");
        res.setSelected(true);
        addLayerChangeTracker(res);

        collab = new JToggleButton("Collaborative Rooms");
        collab.setSelected(true);
        addLayerChangeTracker(collab);

        user = new JToggleButton("User Created POIs");
        user.setSelected(true);
        addLayerChangeTracker(user);

        favs = new JToggleButton("Favourite POIs");
        favs.setSelected(true);
        addLayerChangeTracker(favs);

        JButton access = new JButton("Accessibility");

        checkPan.add(cRoom);
        checkPan.add(nav);
        checkPan.add(wash);
        checkPan.add(entry);
        checkPan.add(genL);
        checkPan.add(res);
        checkPan.add(collab);
        checkPan.add(user);
        checkPan.add(favs);

        layer.add(layerSelect, BorderLayout.NORTH);
        layer.add(checkPan, BorderLayout.CENTER);
        layer.add(access, BorderLayout.SOUTH);

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
        emptyPan.setPreferredSize(new Dimension(200,135));

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

    public static void layerTracker() {
        if (cRoom.isSelected()) {
            MapPanel.toggleLayerOn("Classroom");
        }
        else {
            MapPanel.toggleLayerOff("Classroom");
        }

        if (nav.isSelected()) {
            MapPanel.toggleLayerOn("Navigation");
        }
        else {
            MapPanel.toggleLayerOff("Navigation");
        }

        if (wash.isSelected()) {
            MapPanel.toggleLayerOn("Washroom");
        }
        else {
            MapPanel.toggleLayerOff("Washroom");
        }

        if (entry.isSelected()) {
            MapPanel.toggleLayerOn("Entry / Exit");
        }
        else {
            MapPanel.toggleLayerOff("Entry / Exit");
        }

        if (genL.isSelected()) {
            MapPanel.toggleLayerOn("Lab");
        }
        else {
            MapPanel.toggleLayerOff("Lab");
        }

        if (res.isSelected()) {
            MapPanel.toggleLayerOn("Restaurant");
        }
        else {
            MapPanel.toggleLayerOff("Restaurant");
        }

        if (collab.isSelected()) {
            MapPanel.toggleLayerOn("Collaborative Room");
        }
        else {
            MapPanel.toggleLayerOff("Collaborative Room");
        }

        if (user.isSelected()) {
            MapPanel.toggleLayerOn("User POIs");
        }
        else {
            MapPanel.toggleLayerOff("User POIs");
        }

        if (favs.isSelected()) {
            MapPanel.toggleLayerOn("Favourites");
        }
        else {
            MapPanel.toggleLayerOff("Favourites");
        }
    }

    private void addLayerChangeTracker(JToggleButton toggle1) {
        toggle1.addItemListener(e -> {
                    if (e.getSource() == cRoom) {
                        if (cRoom.isSelected()) {
                            MapPanel.toggleLayerOn("Classroom");
                        }
                        else {
                            MapPanel.toggleLayerOff("Classroom");
                        }
                    }
                    else if (e.getSource() == nav) {
                        if (nav.isSelected()) {
                            MapPanel.toggleLayerOn("Navigation");
                        }
                        else {
                            MapPanel.toggleLayerOff("Navigation");
                        }
                    }
                    else if (e.getSource() == wash) {
                        if (wash.isSelected()) {
                            MapPanel.toggleLayerOn("Washroom");
                        }
                        else {
                            MapPanel.toggleLayerOff("Washroom");
                        }
                    }
                    else if (e.getSource() == entry) {
                        if (entry.isSelected()) {
                            MapPanel.toggleLayerOn("Entry / Exit");
                        }
                        else {
                            MapPanel.toggleLayerOff("Entry / Exit");
                        }
                    }
                    else if (e.getSource() == genL) {
                        if (genL.isSelected()) {
                            MapPanel.toggleLayerOn("Lab");
                        }
                        else {
                            MapPanel.toggleLayerOff("Lab");
                        }
                    }
                    else if (e.getSource() == res) {
                        if (res.isSelected()) {
                            MapPanel.toggleLayerOn("Restaurant");
                        }
                        else {
                            MapPanel.toggleLayerOff("Restaurant");
                        }
                    }
                    else if (e.getSource() == collab) {
                        if (collab.isSelected()) {
                            MapPanel.toggleLayerOn("Collaborative Room");
                        } else {
                            MapPanel.toggleLayerOff("Collaborative Room");
                        }
                    }
                    else if (e.getSource() == user) {
                        if (user.isSelected()) {
                            MapPanel.toggleLayerOn("User POIs");
                        } else {
                            MapPanel.toggleLayerOff("User POIs");
                        }
                    }
                    else if (e.getSource() == favs) {
                        if (favs.isSelected()) {
                            MapPanel.toggleLayerOn("Favourites");
                        } else {
                            MapPanel.toggleLayerOff("Favourites");
                        }
                    }
        });
    }

    /** Disables the side panel components when the ADD poi button is pressed */
    public static void disableSelection() {
        poiList.getComponent(1).setEnabled(false);
        favourites.getComponent(1).setEnabled(false);
        for (Component c: checkPan.getComponents()) {
            if (c instanceof JToggleButton || c instanceof JButton) {
                c.setEnabled(false);
            }
        }

    }

    /** Enables the side panel components when the ADD poi operation has been completed/cancelled
     * @see PoiPanel */
    public static void enableSelection() {
        SidePanel.updateDropDown();             //ADDED RECENTLY TO UPDATE SIDE PANEL
        SidePanel.updateFavourites();

        poiList.getComponent(1).setEnabled(true);
        favourites.getComponent(1).setEnabled(true);
        for (Component c: checkPan.getComponents()) {
            if (c instanceof JToggleButton || c instanceof JButton) {
                c.setEnabled(true);
            }
        }


    }
}
