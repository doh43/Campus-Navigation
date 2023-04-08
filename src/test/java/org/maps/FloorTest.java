package org.maps;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Testing for Floor class */
public class FloorTest {
    FloorTest() {

    }
    @Test
    public void testGetName() {
        System.out.println("Testing getName");
        JSONObject floorJSON = new JSONObject();
        floorJSON.put("name", "asdf");
        floorJSON.put("id", 1);
        floorJSON.put("pois", new JSONArray().put(new JSONObject().put("name", "asdf").put("id", 1).put("desc", "asdf").put("posX", 1).put("posY", 1).put("roomNum", "asdf").put("floor", 1)));
        Floor floor = new Floor(new JSONArray().put(floorJSON), 0);
        assertEquals(floor.getName(), "asdf");
    }
    @Test
    public void testGetId() {
        System.out.println("Testing getId");
        JSONObject floorJSON = new JSONObject();
        floorJSON.put("name", "asdf");
        floorJSON.put("id", 1);
        floorJSON.put("pois", new JSONArray().put(new JSONObject().put("name", "asdf").put("id", 1).put("desc", "asdf").put("posX", 1).put("posY", 1).put("roomNum", "asdf").put("floor", 1)));
        Floor floor = new Floor(new JSONArray().put(floorJSON), 0);
        assertEquals(floor.getId(), 1);
    }
    @Test
    public void testGetPois() {
        System.out.println("Testing getPois");
        JSONObject floorJSON = new JSONObject();
        floorJSON.put("name", "asdf");
        floorJSON.put("id", 1);
        floorJSON.put("pois", new JSONArray().put(new JSONObject().put("name", "asdf").put("id", 1).put("desc", "asdf").put("posX", 1).put("posY", 1).put("roomNum", "asdf").put("floor", 1)));
        Floor floor = new Floor(new JSONArray().put(floorJSON), 0);
        assertEquals(floor.getPois().length(), 1);
    }
}
