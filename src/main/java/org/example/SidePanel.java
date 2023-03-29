/** Implements the side panel of the Map screen
 * @author Tomas Garcia, Ethan Tiger Wakefield
 * @version 1.0
 * @see org.example.MainPanel
 * @see org.example.PoiPanel */
package org.example;

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

    /* Holds all side panel components */
    private JPanel selection;

    /* Represent types of POIs */
    private JCheckBox cRoom, nav, wash, entry, genL, res, collab;

    /** Creates a new side panel
     * Contains a PoiPanel that will hide the side panel components and expand when pressed
     * @ //TODO: 2023-03-29 Change the dropdowns for poiSelect and favourites to access POI data */
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

        String[] pois = {"Classroom1","Rest2","Lab3","Stair"};
        JComboBox<String> cb = new JComboBox<>(pois);

        poiList.add(poiSelect, BorderLayout.NORTH);
        poiList.add(cb, BorderLayout.CENTER);

        // LayerPanel
        layer = new JPanel();
        layer.setLayout(new BorderLayout());
        layer.setBackground(Color.lightGray);
        layer.setPreferredSize(new Dimension(200,220));

        /* Title for the layers panel */
        JLabel layerSelect = new JLabel("Layers");

        checkPan = new JPanel(new GridLayout(7,1));

        cRoom = new JCheckBox("Classrooms",true);
        addChangeListener(cRoom);

        nav = new JCheckBox("Navigation",true);
        addChangeListener(nav);

        wash = new JCheckBox("Washrooms",true);
        addChangeListener(wash);

        entry = new JCheckBox("Entry/Exit",true);
        addChangeListener(entry);

        genL = new JCheckBox("Labs",true);
        addChangeListener(genL);

        res = new JCheckBox("Restaurants",true);
        addChangeListener(res);

        collab = new JCheckBox("Collaborative Rooms",true);
        addChangeListener(collab);

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

    /** Enables or disables a POI type layer from the map
     * Current state: Tracks whether any of the layer checkboxes are selected/deselected
     * @param check1 checkbox whose state is being tracked */
    private void addChangeListener(JCheckBox check1) {
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
