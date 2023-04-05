package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for creating a functional search bar and the corresponding building/floor labels so
 * the user knows which building and floor they are in when they open the map.
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
        floorLabel = new JLabel("<html><b> - "+ floorName +"</b>");
        floorLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 25));

        // Creates the building label
        JLabel buildingLabel = new JLabel("<html><b> "+buildingName+"</b>");
        buildingLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 25));

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
                // This updates the floor map with the map that contains the POI that was searched for
                changeLabel(MapPanel.getFloorNum());
                MapPanel.setUpTypePanels();

                // Creates POI pop-up
                MapPanel.jumpToPoi(new Poi(searchPoi));
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
        Data data = new Data();
        // Loop through all the POIs to see if there is a match with the user's search, if found, return the POI
        for (int j = 0; j < Maps.getMapBuilding().getNumFloors(); j++) {
            // Retrieving all the POI data
            JSONArray jsonPois = data.getPois(Maps.getBuildingCode(), j);
            JSONArray customPois = data.getCustomPOIs(Maps.getBuildingCode(), j);

            JSONArray allPois = new JSONArray();
            allPois.putAll(jsonPois);
            allPois.putAll(customPois);

            int numPois = allPois.length();

            // Creating variables
            String[] poiNames = new String[numPois];
            int[] floorPoiIDs = new int[numPois];
            String[] poiDesc = new String[numPois];
            int[] roomNum = new int[numPois];


            // Loops through the POIs on a floor
            for (int i = 0; i < numPois; i++) {
                JSONObject poi = (JSONObject) jsonPois.get(i);

                // Looks through POI names, ID, and description
                poiNames[i] = poi.getString("name");
                floorPoiIDs[i] = poi.getInt("id");
                poiDesc[i] = poi.getString("desc");
                roomNum[i] = poi.getInt("roomNum");

                // Checking if the name of the POI can be matched
                if (input.equalsIgnoreCase(poiNames[i])) {
                    MapPanel.setFloorNum(j);
                    return poi;
                }

                // Checking if room number of the POI can be matched
                else if (input.equalsIgnoreCase(String.valueOf(roomNum[i]))) {
                    MapPanel.setFloorNum(j);
                    return poi;
                }

                // Checking if the ID of the POI can be matched
                else if (input.equalsIgnoreCase(String.valueOf(floorPoiIDs[i]))) {
                    MapPanel.setFloorNum(j);
                    return poi;
                }

                // Checking if the description of the POI can be matched
                else if (input.equalsIgnoreCase(poiDesc[i])) {
                    MapPanel.setFloorNum(j);
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
            floorLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 25));
        }
    }
