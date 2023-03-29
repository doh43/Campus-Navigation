package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String username;
    private String password;
    private Boolean isDeveloper;
    private List<Poi> favourites; // Store favourite POIs in a list

    /**
     * Class constructor
     *
     * @param username username
     * @param password password
     * @param isDeveloper user type
     */
    public User(String username, String password, boolean isDeveloper) {
        this.username = username;
        this.password = password;
        this.isDeveloper = isDeveloper;
        this.favourites = new ArrayList<>();
    }
    // JSON representation of a user
    protected User(JSONObject jsonObject) {
        this.username = jsonObject.getString("username");
        this.password = jsonObject.getString("password");
        this.isDeveloper = jsonObject.getBoolean("isDeveloper");
    }

    // Method that returns a JSONObject representing the User object
    protected JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("isDeveloper", this.isDeveloper);

        return jsonObject;
    }
    /*
        JSONArray favouritesJsonArray = new JSONArray();
        for (POILocation poiLocation : this.favourites) {
            JSONObject jsonPOILocation = new JSONObject();
            jsonPOILocation.put("building", poiLocation.getBuilding().getName());
            jsonPOILocation.put("floor", poiLocation.getFloor().getName());
            jsonPOILocation.put("poi", poiLocation.getPOI().getRoomNumber());
            favouritesJsonArray.put(jsonPOILocation);
        }
        jsonUser.put("favourites", favouritesJsonArray);
        // custom to json
        JSONArray customJsonArray = new JSONArray();
        if (customPOIs != null) {
            for (POILocation poiLocation : customPOIs) {
                customJsonArray.put(
                        poiLocation.poi.createJSONObjectOfCustomPOI(poiLocation.building, poiLocation.floor));
            }
        }
        jsonUser.put("customPOIs", customJsonArray);

         */

    public void saveUser() {
        JSONObject jsonUser = toJSONObject();
        Utility.writeToFile(jsonUser, "./data/userData/" + this.username + ".json");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isDeveloper() {
        return isDeveloper;
    }


    public void addFavourite() {
        // favourites.add(poiLocation);
    }

    public void removeFavourite() {
        // favourites.remove(poiLocation);
    }




}