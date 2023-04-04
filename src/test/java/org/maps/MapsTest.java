package org.maps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the class Maps
 */
public class MapsTest {

    private static Maps maps;

    @BeforeEach
    public void setUp() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);

        maps = new Maps("mc");
    }

    /**
     * Tests getBuildingCode method in class Maps
     */
    @Test
    public void testGetBuildingCode() {
        assertEquals("mc", Maps.getBuildingCode());
    }

    /**
     * Tests getMapFrame method in class Maps
     */
    @Test
    public void testGetMapFrame() {
        assertNotNull(Maps.getMapFrame());
    }

    /**
     * Tests getMapBuilding method in class Maps
     */
    @Test
    public void testGetMapBuilding() {
        assertNotNull(Maps.getMapBuilding());
    }

}
