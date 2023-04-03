package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for creating the search bar and the corresponding building/floor labels so the user knows
 * which building and floor they are in when they open the map.
 *
 * @author ewakefi, tha7, tgarci3
 */
public class SearchPanel extends JPanel {
    public static JLabel floorLabel;
    Integer floorNum;

    SearchPanel() {

        // Creating the building label for the map
        String building = Maps.getBuildingCode();
        int floorNum = MapPanel.getFloorNum();

        // Retrieves the data to determine the building name and the floor name
        Data d = Data.getInstance();
        Object floorName = d.savedData.getJSONObject(building).getJSONArray("floors").getJSONObject(floorNum).get("name");
        String buildingName = String.valueOf(d.savedData.getJSONObject(building).getString("name"));

        // Creates the floor label
        floorLabel = new JLabel("<html><b> - "+ floorName +"</b>");
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

            // Checks to see if the user's input is valid or not
            JSONObject searchPoi = searchChecker(userInput);

            // If the user's input is invalid, the user will be presented with an error
            if (searchPoi == null) {
                JOptionPane.showMessageDialog(null, "The POI you are searching for cannot be found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // Otherwise, the POI they were searching for will show
            else {
                JSONArray jsonFloors = d.savedData.getJSONObject(building).getJSONArray("floors");
                for (int i = 0; i < jsonFloors.length(); i++) {
                    // This updates the map if the POI they searched for was on a different floor
                    if (floorNum == i) {
                        changeLabel(i);
                        BottomPanel.changeFloor(i);
                    }
                }
                PoiPopup p = new PoiPopup(new Poi(searchPoi));
                p.setVisible(true);
            }
        });
        add(searchPrompt);
        add(searchButton);
        add(buildingLabel);
        add(floorLabel);
    }

    /**
     * This method searches the JSON file to check if whatever the user inputted is in the database, if it is not,
     * then it will return nothing.
     *
     * @param input uses the user's input to compare.
     * @return the POI the user is looking for, if not found, return null.
     */
    private JSONObject searchChecker(String input) {
        // Loop through all the POIs to see if there is a match with the user's search, if found, return the POI
        for (int j = 0; j < Maps.getMapBuilding().getNumFloors(); j++) {

            // Retrieving all the POI data
            JSONArray jsonPois = Maps.getMapBuilding().getFloors()[j].getPois();
            int numPois = jsonPois.length();

            // Creating variables
            String[] poiNames = new String[numPois];
            int[] floorPoiIDs = new int[numPois];
            String[] poiDesc = new String[numPois];
            for (int i = 0; i < jsonPois.length(); i++) {
                JSONObject poi = (JSONObject) jsonPois.get(i);

                // Looks through POI names, ID, and description
                poiNames[i] = poi.getString("name");
                floorPoiIDs[i] = poi.getInt("id");
                poiDesc[i] = poi.getString("desc");
                System.out.print(poiNames[i]);

                // Checking if the name of the POI can be matched
                if (input.equalsIgnoreCase(poiNames[i])) {
                    Integer floorNum = Maps.getMapBuilding().getFloors()[i].getId();
                    return poi;
                }

                // Checking if the ID of the POI can be matched
                else if (input.equalsIgnoreCase(String.valueOf(floorPoiIDs[i]))) {
                    Integer floorNum = Maps.getMapBuilding().getFloors()[i].getId();
                    return poi;
                }

                // Checking if the description of the POI can be matched
                else if (input.equalsIgnoreCase(poiDesc[i])) {
                    Integer floorNum = Maps.getMapBuilding().getFloors()[i].getId();
                    return poi;
                }
            }
        }
        return null;
    }

        /**
         * When the method is called, it will change the floor label beside the search bar, it essentially refreshes the
         * top panel of the GUI.
         *
         * @param floor takes in the floor number that the user selects.
         */
        public static void changeLabel (int floor) {
            String buildingName = Maps.getBuildingCode();
            Data d = Data.getInstance();
            Object name = d.savedData.getJSONObject(buildingName).getJSONArray("floors").getJSONObject(floor).get("name");
            floorLabel.setText(("<html><b> - " + name.toString() + "</b>"));
            floorLabel.setFont(new java.awt.Font("Segoe UI", 0, 25));
        }
    }

