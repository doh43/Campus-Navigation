package org.example;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JLayeredPane {
    JPanel poiList;
    JPanel layer;
    JPanel favourites;
    JPanel poiPanel;
    JPanel selection;
    SidePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(200,1000));

        selection = new JPanel();
        selection.setBounds(0,0,200,600);

        // PoiListPanel
        poiList = new JPanel();
        poiList.setLayout(new BorderLayout());
        poiList.setBackground(Color.lightGray);
        poiList.setPreferredSize(new Dimension(200,150));

        JLabel poiSelect = new JLabel("Select POI:");
        String[] pois = {"Classroom1","Rest2","Lab3","Stair"};
        JComboBox<String> cb = new JComboBox<>(pois);



        poiList.add(poiSelect, BorderLayout.NORTH);
        poiList.add(cb, BorderLayout.CENTER);

        // LayerPanel
        layer = new JPanel();
        layer.setLayout(new GridLayout(8,1));
        layer.setBackground(Color.lightGray);
        layer.setPreferredSize(new Dimension(200,300));

        JLabel layerSelect = new JLabel("Layers");

        JCheckBox cRoom = new JCheckBox("Classrooms",true);
        JCheckBox nav = new JCheckBox("Navigation",true);
        JCheckBox wash = new JCheckBox("Washrooms",true);
        JCheckBox entry = new JCheckBox("Entry/Exit",true);
        JCheckBox genL = new JCheckBox("Labs",true);
        JCheckBox res = new JCheckBox("Restaurants",true);
        JCheckBox collab = new JCheckBox("Collaborative Rooms",true);


        layer.add(layerSelect);
        layer.add(cRoom);
        layer.add(nav);
        layer.add(wash);
        layer.add(entry);
        layer.add(genL);
        layer.add(res);
        layer.add(collab);

        // Favourites
        favourites = new JPanel();
        favourites.setLayout(new BorderLayout());
        favourites.setBackground(Color.lightGray);
        favourites.setPreferredSize(new Dimension(200,150));

        JLabel favSelect = new JLabel("Favourites");
        String[] favs = {"fav1","fav2","fav3","fav4"};
        JComboBox<String> favBox = new JComboBox<>(favs);

        favourites.add(favSelect, BorderLayout.NORTH);
        favourites.add(favBox, BorderLayout.CENTER);

        selection.add(poiList);
        selection.add(layer);
        selection.add(favourites);

        add(selection, Integer.valueOf(0));
        add(new PoiPanel(), Integer.valueOf(1));

    }

}
