package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String username;
    private String password;
    private Boolean isDeveloper;
    private List<Favourites> favourites;

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

    /**
     * JSON representation of a user
     * @param jsonObject
     */
    protected User(JSONObject jsonObject) {
        this.username = jsonObject.getString("username");
        this.password = jsonObject.getString("password");
        this.isDeveloper = jsonObject.getBoolean("isDeveloper");
    }

    /**
     *
     * @return JSONObject representing the User object
     */
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("isDeveloper", this.isDeveloper);
        JSONArray favouritesJsonArray = new JSONArray();
        for (Favourites favourites : this.favourites) {
            JSONObject jsonPOILocation = new JSONObject();
            jsonPOILocation.put("building", favourites.getBuilding().getName());
            jsonPOILocation.put("floor", favourites.getFloor().getId());
            jsonPOILocation.put("poi", favourites.getRoomName().getName());
            favouritesJsonArray.put(jsonPOILocation);
        }
        jsonObject.put("favourites", favouritesJsonArray);

        return jsonObject;
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

    public List<Favourites> getFavourites() {
        return this.favourites;
    }

    public void addFavourite(Favourites fav) {
        favourites.add(fav);
    }

    public void removeFavourite(Favourites fav) {
       favourites.remove(fav);
    }

}