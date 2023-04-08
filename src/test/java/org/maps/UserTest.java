/** JUnit testing for User class
 * @author Taejun Ha */

package org.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {


    /** Test the getUsername method of class User */
    @Test
    public void testGetUsername() {
        System.out.println("Testing getUsername");
        User user = new User("john_doe", "password123", "testData");
        assertEquals("john_doe", user.getUsername());
    }

    /** Test the userType method of class User */
    @Test
    public void testUserType() {
        System.out.println("Testing userType");
        User user = new User("john_doe", "password123", "testData");
        assertEquals("testData", user.userType());
    }
}
