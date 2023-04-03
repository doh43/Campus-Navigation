package org.maps;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserData {
    private User user;
    private JSONObject data;

    public UserData(User user) {
        this.user = user;
        this.data = loadUserData();
    }

    private JSONObject loadUserData() {
        try {
            String userContents =  new String(Files.readAllBytes(Paths.get("./data/userData/" + user.getUsername() + ".json")));
            return new JSONObject(userContents);
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject userData = new JSONObject();
        }
        return null;
    }


}

