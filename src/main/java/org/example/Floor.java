package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class Floor {
    private String name;
    private int id;
    private JSONArray pois;
    Floor(JSONArray floors, int idx) {
        JSONObject floor = (JSONObject) floors.get(idx);
        name = floor.getString("name");
        id = floor.getInt("id");
        pois = (JSONArray) floor.get("pois");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public JSONArray getPois() {
        return pois;
    }
}
