package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import org.json.simple.JSONObject;
/**

 This class represents the data manager responsible for storing and retrieving data from the buildings.json file.
 */
public final class Data {

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
        try (FileWriter file = new FileWriter("./data/buildings.json")) {
            file.write(o.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
        return savedData.getJSONObject(building).getJSONArray("floors").getJSONObject(floorNum - 1).getJSONArray("pois");
    }

    /**
     * Adds a custom POI to the user's customPOIs array
     * @param object the POI to be added.
     *
     */

    public void addCustomPOI(JSONObject object) {
        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader("./data/userData/" + sessionManager.getCurrentUser().getUsername() + ".json")));
            JSONArray customPOIs = jsonObject.getJSONArray("customPOIs");
            customPOIs.put(object);
            FileWriter fileWriter = new FileWriter("./data/userData/" + sessionManager.getCurrentUser().getUsername() + ".json");
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getCustomPOIs(String building, int floorNum) {
         return userData.getJSONArray("customPOIs").
                 getJSONObject(0).
                 getJSONObject(building).
                 getJSONArray("floors").
                 getJSONObject(floorNum - 1).
                 getJSONArray("pois");
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


