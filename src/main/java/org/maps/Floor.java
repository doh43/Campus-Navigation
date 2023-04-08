package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

/** Provides a way to work with JSON floor data abstractly
 * @author Tomas Garcia
 * @version 1.0
 * @see org.maps.Building */
public class Floor {
    /** Name of the floor */
    private String name;

    /** ID of the floor */
    private int id;

    /** POIs in the floor */
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

    /** Returns the name of the floor
     * @return Name of the floor */
    public String getName() {
        return name;
    }

    /** Returns the id of the floor
     * @return ID of the floor */
    public int getId() {
        return id;
    }

    /** Returns the POIs in the floor
     * @return POIs in the floor */
    public JSONArray getPois() {
        return pois;
    }
}
