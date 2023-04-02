package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

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
        String building = Maps.getBuildingCode();
        int floorNum = MapPanel.getFloorNum();

        // Retrieves the data to determine the building name and the floor name
        Data d = Data.getInstance();
        Object floorName = d.savedData.getJSONObject(building).getJSONArray("floors").getJSONObject(floorNum).get("name");
        String buildingName = String.valueOf(d.savedData.getJSONObject(building).getString("name"));

        // Creates the floor label
        floorLabel = new JLabel("<html><b> - "+(String) floorName+"</b>");
        floorLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        // Creates the building label
        JLabel buildingLabel = new JLabel("<html><b> "+buildingName+"</b>");
        buildingLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));

        setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField searchPrompt = new JTextField("Search for POIs");
        searchPrompt.setPreferredSize(new Dimension(190,25));

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // Saves the user input
            String userInput = searchPrompt.getText();

            // Retrieving all of the POI data
            int i = 0;
            JSONArray jsonPois = Maps.getMapBuilding().getFloors()[i].getPois();
            int numPois = jsonPois.length();

            // Creating variables
            String[] poiNames = new String[numPois];
            int[] floorPoiIDs = new int[numPois];
            String[] poiDesc = new String[numPois];

            // Loop through all the POIs to see if there is a match with the use search, if found, there is a pop-up
            for (i = 0; i < jsonPois.length(); i++) {
                JSONObject poi = (JSONObject) jsonPois.get(i);
                poiNames[i] = poi.getString("name");
                floorPoiIDs[i] = poi.getInt("id");
                poiDesc[i] = poi.getString("desc");
                System.out.println(poiDesc[i]); // TEST REMOVE LATER

                // Checking if the name of the POI can be matched
                if (userInput.equalsIgnoreCase(poiNames[i])) {
                    PoiPopup p = new PoiPopup(new Poi(poi));
                    p.setVisible(true);

                    else {
                        JOptionPane.showMessageDialog(null, "Sorry, what you searched for cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Checking if the id of the POI can be matched
                if (userInput.equalsIgnoreCase(String.valueOf(floorPoiIDs[i]))) {
                    PoiPopup p = new PoiPopup(new Poi(poi));
                    p.setVisible(true);
                }

                // Checking if the POI description can be matched
                if (userInput.equalsIgnoreCase(poiDesc[i])) {
                    PoiPopup p = new PoiPopup(new Poi(poi));
                    p.setVisible(true);
                }

                // If there is no match, the user will be presented with a pop-up message stating that there search cannot be matched.
//                else {
//                    JOptionPane.showMessageDialog(null, "Sorry, what you searched for cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }
        });

        add(searchPrompt);
        add(searchButton);
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
