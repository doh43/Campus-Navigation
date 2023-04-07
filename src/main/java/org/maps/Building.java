/** Provides a way to work with JSON building data abstractly
 * @author Tomas Garcia
 * @version 1.0
 * @see org.maps.Floor */

package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

/** Provides a way to work with JSON building data abstractly
 * @author Tomas Garcia
 * @version 1.0
 * @see org.maps.Floor */
public class Building {
    /** The name of the building */
    private String name;

    /** The floors of the building */
    private Floor[] floors;

    /** The number of floors in the building */
    private int numOfFloors;

    /** The buildings JSON that contains metadata for all buildings */
    private JSONObject buildings;

    /** Creates a new building
     * @param buildingCode building being accessed from buildings */
    Building(String buildingCode) {
        Data d = Data.getInstance();
        buildings = d.savedData;

        /* Creates a JSON object storing the specified building */
        JSONObject build = buildings.getJSONObject(buildingCode);

        /* Uses the information from build to get the building's name */
        name = build.getString("name");

        /* Uses the information from build to get the number of floors in the building */
        numOfFloors = build.getInt("numFloors");
        JSONArray floorList = build.getJSONArray("floors");

        /* Creates a floor array storing the building's floors */
        floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++) {
            floors[i] = new Floor(floorList, i);
        }
    }

    /** Returns the name of the building
     * @return Name of the building */
    public String getName() {
        return name;
    }

    /** Returns the floors of the building
     * @return Floors of the building */
    public Floor[] getFloors() {
        return floors;
    }

    /** Returns the number of floors in the building
     * @return Number of floors in the building */
    public int getNumFloors() {
        return numOfFloors;
    }
}
