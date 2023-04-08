/** Testing for Building class
 * @author tha7 */

package org.maps;

import org.junit.Test;
import static org.junit.Assert.*;

/** Testing for Building class */
public class BuildingTest {

    /** Testing for getName in Building class */
    @Test
    public void testGetName() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);

        String expectedName = "Middlesex College";
        Building build = new Building("mc");
        assertEquals(expectedName, build.getName());
    }

    /** Test for getNumFloors in Building class */
    @Test
    public void testGetNumFloors() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);

        Building build = new Building("mc");
        assertEquals(5, build.getNumFloors());
    }

    /** Test for getFloors in Building class */
    @Test
    public void testGetFloors() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);
        Building build = new Building("mc");

        Floor[] floors = build.getFloors();
        assertNotNull(floors);
        assertEquals(5, floors.length);
    }
}
