package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * User class
 * @author Daniel Oh
 * @version 2.0 (working on customPOI array)
 */

public class User {
    private static User instance;
    private String username;
    private String password;
    private String userType;
    private ArrayList<POILocation> favourites;
    private ArrayList<POILocation> customPOIs;

    /**
     * Class constructor
     *
     * @param username username
     * @param password password
     */
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = "base";
        this.favourites = new ArrayList<POILocation>();
        this.customPOIs = new ArrayList<POILocation>();
    }

    /**
     * JSON representation of a user
     * @param jsonObject
     */
    protected User(JSONObject jsonObject) {
        this.username = jsonObject.getString("username");
        this.password = jsonObject.getString("password");
        this.userType = jsonObject.getString("userType");
    }

    /**
     *
     * @return JSONObject representing the User object
     */
    public JSONObject toJSONObject(List<POILocation> customPOIs) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("userType", "base");
        JSONArray favouritesJsonArray = new JSONArray();
        for (POILocation poiLocation : this.favourites) {
            JSONObject jsonPOILocation = new JSONObject();
            jsonPOILocation.put("building", poiLocation.getBuilding().getName());
            jsonPOILocation.put("floor", poiLocation.getFloor().getId());
            jsonPOILocation.put("poi", poiLocation.getRoomName().getName());
            favouritesJsonArray.put(jsonPOILocation);
        }
        jsonObject.put("favourites", favouritesJsonArray);

        /* populating customPOI array */
        JSONArray customJsonArray = new JSONArray();


        if (customPOIs != null) {
            for (POILocation poiLocation : customPOIs) {
                customJsonArray.put(poiLocation.poi.createJSONObjectOfCustomPOI(poiLocation.building, poiLocation.floor));
            }
        }
        jsonObject.put("customPOIs", customJsonArray);
        return jsonObject;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String userType() {
        return userType;
    }

    public List<POILocation> getFavourites() {
        return this.favourites;
    }

    public void addFavourite(POILocation fav) {
        favourites.add(fav);
    }

    public void removeFavourite(POILocation fav) {
       favourites.remove(fav);
    }

}