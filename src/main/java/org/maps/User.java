package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * User class
 * @author Daniel Oh
 * @version 2.0 (working on customPOI array)
 */
public class User {
    private String username;
    private String password;
    private String userType;

    /**
     * Class constructor
     *
     * @param username username
     * @param password password
     */
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Creates a JSONObject to hold user data inside the user's json file
     * @return JSONObject representing the User object
     */
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", this.username);
        jsonObject.put("password", this.password);
        jsonObject.put("userType", "base");

        JSONArray favouritesJsonArray = new JSONArray();
        jsonObject.put("favourites", favouritesJsonArray);

        /* populating customPOI array */
        JSONArray customJsonArray = new JSONArray();
        try {
            JSONObject PoiTemplate = new JSONObject(new JSONTokener(new FileReader("./data/template.json")));
            customJsonArray.put(PoiTemplate);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        jsonObject.put("customPOIs", customJsonArray);
        return jsonObject;
    }

    public String getUsername() {
        return username;
    }

    public String userType() {
        return userType;
    }
}