/** Provides a way to work with JSON floor data abstractly
 * @author Tomas Garcia
 * @version 1.0
 * @see org.example.Building */
package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class Floor {
    /* Name of the floor */
    private String name;

    /* ID of the floor */
    private int id;

    /* POIs in the floor */
    private JSONArray pois;

    /** Creates a new Floor
     * @param floors Array of floors for a building
     * @param idx Specific floor being created */
    Floor(JSONArray floors, int idx) {
        /* Creates a JSON object storing the specified floor */
        JSONObject floor = (JSONObject) floors.get(idx);

        /* Uses information from floor to get the floor name */
        name = floor.getString("name");

        /* Uses information from floor to get the floor id */
        id = floor.getInt("id");

        /* Uses information from floor to get the floor's POIs */
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
