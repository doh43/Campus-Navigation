package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Building {
    private String name;
    private Floor[] floors;
    private int numOfFloors;
    private JSONObject buildings;
    Building(String buildingCode) {
        openBuildings();
        JSONObject build = buildings.getJSONObject(buildingCode);

        name = build.getString("name");

        numOfFloors = build.getInt("numFloors");
        JSONArray floorList = build.getJSONArray("floors");

        floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++) {
            floors[i] = new Floor(floorList, i);
        }
    }

    private void openBuildings() {
        try {
            String contents =  new String(Files.readAllBytes(Paths.get("./data/buildings.json")));
            buildings = new JSONObject(contents);
        } catch (IOException e) {
            e.printStackTrace();
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


    // TODO delete main
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
