/** Provides a way to work with JSON building data abstractly
 * @author Tomas Garcia
 * @version 1.0
 * @see org.maps.Floor */
package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Building {
    /* The name of the building */
    private String name;

    /* The floors of the building */
    private Floor[] floors;

    /* The number of floors in the building */
    private int numOfFloors;

    /* The buildings JSON that contains metadata for all buildings */
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

    public String getName() {
        return name;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public int getNumFloors() {
        return numOfFloors;
    }

    /** Hides a POI layer from a building
     * @ //TODO: make sure this method is necessary, if so, finish it */
    public void hidePOILayer() {
        for (int i = 0; i < numOfFloors; i++) {
            floors[i].getPois();
        }
    }
    /** Used to test the class
     * @ //TODO: delete main after testing */
    public static void main(String[] args) {
        Building build1 = new Building("mc");
        System.out.println(build1.getName());
        System.out.println(build1.getNumFloors());

        Building build2 = new Building("ah");
        System.out.println(build2.getName());
        System.out.println(build2.getNumFloors());

        Building build3 = new Building("ncb");
        System.out.println(build3.getName());
        System.out.println(build3.getNumFloors());
    }
}
