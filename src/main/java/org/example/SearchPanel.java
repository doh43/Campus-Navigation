package org.example;

import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * This class is responsible for creating the search bar and the corresponding building/floor labels so the user knows
 * which building and floor they are in when they open the map.
 *
 * @author ewakefi, tha7, tgarci3
 */
public class SearchPanel extends JPanel {
    public static JLabel floorLabel;
    SearchPanel() {

        // Creating the building label for the map
        String buildingName = Maps.getBuildingCode();
        int floorNum = MapPanel.getFloorNum();

        // Retrieves the data to determine the building name and the floor name
        Data d = Data.getInstance();
        Object floorName = d.savedData.getJSONObject(buildingName).getJSONArray("floors").getJSONObject(floorNum-1).get("name");
        buildingName = String.valueOf(d.savedData.getJSONObject(buildingName).getString("name"));

        // Creates the floor label
        floorLabel = new JLabel("<html><b> - "+(String) floorName+"</b>");
        floorLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        // Creates the building label
        JLabel buildingLabel = new JLabel("<html><b> "+buildingName+"</b>");
        buildingLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField searchPrompt = new JTextField("Search for POIs");
        searchPrompt.setPreferredSize(new Dimension(190,25));

        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(25,25));
        searchButton.addActionListener(e -> {
            // Saves the user input (needs to be used for search NOT IMPLEMENTED YET)
            String s1 = searchPrompt.getText();
        });

        add(searchButton);
        add(searchPrompt);
        add(buildingLabel);
        add(floorLabel);
    }

    /**
     * When the method is called, it will change the floor label beside the search bar, it essentially refreshes the
     * top panel of the GUI.
     *
     * @param floor takes in the floor number that the user selects.
     */
    public static void changeLabel(int floor) {
        String buildingName = Maps.getBuildingCode();
        Data d = Data.getInstance();
        Object name = d.savedData.getJSONObject(buildingName).getJSONArray("floors").getJSONObject(floor).get("name");
        floorLabel.setText(("<html><b> - "+name.toString()+"</b>"));
        floorLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));
    }

}
