package org.example;
import javax.swing.*;
import java.awt.*;

public class Maps extends JFrame implements ActionListener {
    
    Maps() {
      
        JPanel sidePanel = new JPanel(new GridLayout(4,1));
        sidePanel.setPreferredSize(new Dimension(200,1000));

        JPanel poiPanel = new JPanel(new BorderLayout());
        poiPanel.setBackground(Color.lightGray);

        JLabel poiSelect = new JLabel("Select POI:");

        String[] pois = {"Classroom1","Rest2","Lab3","Stair"};
        JComboBox<String> cb = new JComboBox<>(pois);

        poiPanel.add(poiSelect, BorderLayout.NORTH);
        poiPanel.add(cb, BorderLayout.CENTER);

        JPanel layerPanel = new JPanel(new GridLayout(7,1));
        layerPanel.setBackground(Color.lightGray);

        JLabel layerSelect = new JLabel("Layers");

        JCheckBox cRoom = new JCheckBox("Classrooms",true);
        JCheckBox nav = new JCheckBox("Navigation",true);
        JCheckBox wash = new JCheckBox("Washrooms",true);
        JCheckBox entry = new JCheckBox("Entry/Exit",true);
        JCheckBox genL = new JCheckBox("Gen Labs",true);
        JCheckBox res = new JCheckBox("Restaurants",true);

        layerPanel.add(layerSelect);
        layerPanel.add(cRoom);
        layerPanel.add(nav);
        layerPanel.add(wash);
        layerPanel.add(entry);
        layerPanel.add(genL);
        layerPanel.add(res);

        JPanel favPanel = new JPanel(new BorderLayout());
        favPanel.setBackground(Color.lightGray);

        JLabel favSelect = new JLabel("Favorites");
        String[] favs = {"fav1","fav2","fav3","fav4"};
        JComboBox<String> favBox = new JComboBox<>(favs);

        favPanel.add(favSelect, BorderLayout.NORTH);
        favPanel.add(favBox, BorderLayout.CENTER);

        JPanel addPanel = new JPanel(new BorderLayout());
        addPanel.setBackground(Color.lightGray);

        JLabel addSelect = new JLabel("Add POI");
        JButton poiAdd = new JButton("+");

        addPanel.add(addSelect, BorderLayout.NORTH);
        addPanel.add(poiAdd, BorderLayout.CENTER);

        sidePanel.add(poiPanel);
        sidePanel.add(layerPanel);
        sidePanel.add(favPanel);
        sidePanel.add(addPanel);

        JPanel mapHolder = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        final JTextField searchPrompt = new JTextField("Search for POIs");
        searchPrompt.setPreferredSize(new Dimension(190,25));

        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(25,25));
        searchButton.addActionListener(e -> {
            String s1 = searchPrompt.getText();
        });

        searchPanel.add(searchButton);
        searchPanel.add(searchPrompt);

        JPanel map = new JPanel(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource("data/maps/ah/ah1.png")));
        JScrollPane mapScroll = new JScrollPane(imageLabel);

        map.add(mapScroll);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(0,0,0,65));

        JPanel floorPanel = new JPanel(new GridLayout());

        JToggleButton floor1 = new JToggleButton("1",true);
        JToggleButton floor2 = new JToggleButton("2");
        JToggleButton floor3 = new JToggleButton("3");
        JToggleButton floor4 = new JToggleButton("4");

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            if (e.getSource() == back) {
                dispose();
                new LandingPage();

            }
        });

        floorPanel.add(floor1);
        floorPanel.add(floor2);
        floorPanel.add(floor3);
        floorPanel.add(floor4);

        bottomPanel.add(floorPanel, BorderLayout.WEST);
        bottomPanel.add(back, BorderLayout.EAST);

        mapHolder.add(searchPanel, BorderLayout.NORTH);
        mapHolder.add(map, BorderLayout.CENTER);
        mapHolder.add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Western Campus Navigation - Map Page");
        setLayout(new BorderLayout());
        setSize(1400,1000);
        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
      
        // Did not add the POI panel because I couldn't run it and couldn't see where it should go

        add(sidePanel, BorderLayout.WEST);
        add(mapHolder, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        Maps frame = new Maps();
    }
}
