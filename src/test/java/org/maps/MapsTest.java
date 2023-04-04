package org.maps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MapsTest {

    private static Maps maps;

    @BeforeAll
    static void setUp() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);

        maps = new Maps("mc");
    }

    @Test
    void testGetBuildingCode() {
        assertEquals("mc", Maps.getBuildingCode());
    }

    @Test
    void testGetMapFrame() {
        assertNotNull(Maps.getMapFrame());
    }

    @Test
    void testGetMapBuilding() {
        assertNotNull(Maps.getMapBuilding());
    }

}
