package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class UserEdits extends User {
    /**
     * Class constructor
     *
     * @param username    username
     * @param password    password
     * @param isDeveloper user type
     */
    public UserEdits(String username, String password, boolean isDeveloper) {
        super(username, password, isDeveloper);
    }



    /** Write a JSON object to the user file */
    public static void writeToFile(JSONObject json, String relativePath) {
        try {
            FileWriter file = new FileWriter(relativePath);
            file.write(json.toString());
            file.flush();
            file.close();
        } catch (IOException ignored) {
            System.out.println("Could not write to file: " + relativePath);
        }
    }



}
