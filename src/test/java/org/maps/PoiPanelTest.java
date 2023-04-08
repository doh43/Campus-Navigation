package org.maps;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Test for the class PoiPanel */
public class PoiPanelTest {
    PoiPanelTest() {

    }
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Setting up class");
        SessionManager.setCurrentUser(new User("asdf", "asdf", "base"));
    }
    @Test
    public void testCheckFormUsed() {
        System.out.println("Testing checkFormUsed");
        PoiPanel poiPanel = new PoiPanel();
        assertEquals(poiPanel.checkFormUsed(), false);
    }
    @Test
    public void testCheckFormUsed2() {
        System.out.println("Testing checkFormUsed2");
        PoiPanel poiPanel = new PoiPanel();
        poiPanel.getPoiName().setText("asdf");
        assertEquals(poiPanel.checkFormUsed(), true);
    }
    @Test
    public void testCheckFormUsed3() {
        System.out.println("Testing checkFormUsed3");
        PoiPanel poiPanel = new PoiPanel();
        poiPanel.getPoiDesc().setText("asdf");
        assertEquals(poiPanel.checkFormUsed(), true);
    }
    @Test
    public void testCheckFormUsed4() {
        System.out.println("Testing checkFormUsed4");
        PoiPanel poiPanel = new PoiPanel();
        poiPanel.getPoiRoomNum().setText("asdf");
        assertEquals(poiPanel.checkFormUsed(), true);
    }
    @Test
    public void testCheckFormUsed5() {
        System.out.println("Testing checkFormUsed5");
        PoiPanel poiPanel = new PoiPanel();
        poiPanel.getMousePosAbsolute().x = 1;
        assertEquals(poiPanel.checkFormUsed(), true);
    }
}
