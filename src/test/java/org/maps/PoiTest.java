package org.maps;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing for POI class
 *
 * @author tha7
 */
public class PoiTest {

    @BeforeEach
    public void setUp() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);

    }

    /**
     * Tests getName method of class Poi
     */
    @Test
    public void testGetName() {
        System.out.print("Test getName");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        String expectedResult = "Test POI";
        String result = instance.getName();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getRoomNum method of class POI
     */
    @Test
    public void testGetRoomNum() {
        System.out.print("Test getRoomNum");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        Integer expectedResult = 10;
        Integer result = instance.getRoomNum();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getType method of class POI
     */
    @Test
    public void testGetType() {
        System.out.print("Test getType");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        String expectedResult = "Test type";
        String result = instance.getType();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getDesc method of class POI
     */
    @Test
    public void testGetDesc() {
        System.out.print("Test getDesc");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        String expectedResult = "This is a test POI";
        String result = instance.getDesc();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getPosX method of class POI
     */
    @Test
    public void testGetPosX() {
        System.out.print("Test getPosX");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        Integer expectedResult = 100;
        Integer result = instance.getPosX();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getGetPosY method of class POI
     */
    @Test
    public void testGetPosY() {
        System.out.print("Test getPosY");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 110);
        Integer expectedResult = 110;
        Integer result = instance.getPosY();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getBuiltIn method of class POI
     */
    @Test
    public void testGetBuiltIn() {
        System.out.print("Test getBuiltIn");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        Boolean expectedResult = false;
        Boolean result = instance.getBuiltIn();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests getId method of class POI
     */
    @Test
    public void testGetId() {
        System.out.print("Test getId");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 100, 100);
        Integer expectedResult = 1;
        Integer result = instance.getId();
        assertEquals(expectedResult, result);
    }

    /**
     * Tests convertJSON method of class POI
     */
    @Test
    public void testConvertJSON() {
        System.out.print("Test convertJSON");

        Poi instance = new Poi("Test POI", "Test type", 1, 10, "This is a test POI", "default", 500, 600);

        JSONObject newPOI = new JSONObject();
        newPOI.put("name", "Test POI");
        newPOI.put("type", "Test type");
        newPOI.put("id", 1);
        newPOI.put("roomNum", 10);
        newPOI.put("desc", "This is a test POI");
        newPOI.put("icon", "default");
        newPOI.put("posX", 500);
        newPOI.put("posY", 600);
        newPOI.put("builtIn", false);
        newPOI.put("favourited", false);

        String expected = newPOI.toString();
        String result = instance.convertJSON().toString();

        assertEquals(expected, result);
    }
}
