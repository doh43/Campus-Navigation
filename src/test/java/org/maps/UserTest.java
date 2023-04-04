package org.maps;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * JUnit testing for User class
 *
 * @author tha7
 */
public class UserTest {


    /**
     * Test the getUsername method of class User
     */
    @Test
    public void testGetUsername() {
        System.out.print("Testing getUsername");
        User user = new User("john_doe", "password123", "testData");
        assertEquals("john_doe", user.getUsername());
    }

    /**
     * Test the userType method of class User
     */
    @Test
    public void testUserType() {
        System.out.print("Testing userType");
        User user = new User("john_doe", "password123", "testData");
        assertEquals("testData", user.userType());
    }
}
