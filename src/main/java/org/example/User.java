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

    /* JSON representation of a user
    public User(JSONObject jsonUser) {
        this(jsonUser.getString("username"), jsonUser.getString("password"),
                UserType.valueOf(jsonUser.getString("isDeveloper")));
    }


    protected JSONObject jsonUser(List<Poi> customPOIs) {
        JSONObject user = new JSONObject();
        user.put("Username", this.username);
        user.put("Password", this.password);
        user.put("isDeveloper", this.isDeveloper);

        JSONArray favouritesArray = new JSONArray();


    }
    */
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