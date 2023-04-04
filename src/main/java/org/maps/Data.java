package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 This class represents the data manager responsible for storing and retrieving data from the buildings.json file.
 */
public final class Data {

    /**
     The singleton instance of the session manager.
     */
    SessionManager sessionManager;
    /**
     The singleton instance of the data manager.
     */
    private static Data INSTANCE;

    /**
     The saved data read from the user's JSON file.
     */
    public JSONObject userData;
    /**
     The saved data read from the JSON file.
     */
    public JSONObject savedData;
    /**
     Creates a new instance of the data manager and loads the saved data from the JSON file.
     */

    Data() {
        loadData();
        loadUserData();
    }
    /**
     Returns the singleton instance of the data manager.
     @return The singleton instance of the data manager.
     */
    public static Data getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Data();
        }
        return INSTANCE;
    }
    /**
     Stores the given JSON object as the new data in the JSON file.
     @param o The JSON object to store as the new data.
     */
    public void storeData(JSONObject o) {
        if (SessionManager.getCurrentUser().getUsername().equals("admin")) {
            try (FileWriter file = new FileWriter("./data/buildings.json")) {
                file.write(o.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter file = new FileWriter("./data/userData/" + SessionManager.getCurrentUser().getUsername() + ".json")) {
                file.write(o.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     Loads the saved data from the JSON file and stores it in the {@code savedData} field.
     */
    public void loadData() {
        try {
            String contents =  new String(Files.readAllBytes(Paths.get("./data/buildings.json")));
            savedData = new JSONObject(contents);
//            System.out.println(savedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     Returns the POIs for the given building and floor number from the saved data.
     @param building The name of the building to get the POIs for.
     @param floorNum The number of the floor to get the POIs for.
     @return The JSONArray containing the POIs for the given building and floor number.
     */
    public JSONArray getPois(String building, int floorNum) {
        return savedData.getJSONObject(building).getJSONArray("floors").getJSONObject(floorNum).getJSONArray("pois");
    }

    /**
     Returns the custom POIs for the given building and floor number from the user's JSON file.
     @param building The name of the building to get the POIs for.
     @param floorNum The number of the floor to get the POIs for.
     @return The JSONArray containing the POIs for the given building and floor number.
     */
    public JSONArray getCustomPOIs(String building, int floorNum) {
         return userData.getJSONArray("customPOIs").
                 getJSONObject(0).
                 getJSONObject(building).
                 getJSONArray("floors").
                 getJSONObject(floorNum).
                 getJSONArray("pois");
    }

    /**
     * Returns the user's favourites from the user's JSON file.
     * @return The JSONArray containing the user's favourites.
     */
    public JSONArray getFavourites() {
        return userData.getJSONArray("favourites");
    }

    /**
     * Loads the user's custom POIs from the user's JSON file.
     */
    public void loadUserData() {
        try {
            String contents =  new String(Files.readAllBytes(Paths.get("./data/userData/" + sessionManager.getCurrentUser().getUsername() + ".json")));
            userData = new JSONObject(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


