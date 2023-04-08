package org.maps;

import org.json.JSONObject;

/**
 * The purpose of this class is to perform a stress test on the application for POIs.
 *
 * @author Taejun Ha
 */
public class PoiStressTest {

    /**
     * The main method for the PoiStressTest class.
     *
     * @param args the command line arguments
     */
    public static void main(String args []) {
        // Create a new user to make custom POIs
        User user = new User("test", "1234", "base");
        SessionManager.setCurrentUser(user);

        // Number of POIs you want to create
        int test = 100;
        for (int i = 0; i < test; i++) {
            int roomNum = i;
            JSONObject testPoi = new Poi("test" + i, "test", roomNum, roomNum, "test", "test", 100, 100).convertJSON();
            Data d = Data.getInstance();
            d.getCustomPOIs("ncb", 2).put(testPoi);
            d.storeData(d.userData);
        }
    }
}
