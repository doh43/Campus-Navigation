package org.example;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Data {
    private static Data INSTANCE;
    public JSONObject savedData;
    Data() {
        loadData();
    }
    public static Data getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Data();
        }
        return INSTANCE;
    }
    public void storeData(JSONObject o) {
        try (FileWriter file = new FileWriter("./data/buildings.json")) {
            file.write(o.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            String contents =  new String(Files.readAllBytes(Paths.get("./data/buildings.json")));
            savedData = new JSONObject(contents);
//            System.out.println(savedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
