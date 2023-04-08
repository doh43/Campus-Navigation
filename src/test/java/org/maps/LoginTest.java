package org.maps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Testing for Login class */
public class LoginTest {

    /** Test for login of base user with correct credentials */
    @Test
    public void testUser() {
        User user = new User("john_doe", "password123", "base");
        SessionManager.setCurrentUser(user);
        assertEquals("john_doe", user.getUsername());
    }

    /** Test for login of admin user with correct credentials */
    @Test
    public void testAdmin() {
        User user = new User("admin", "cs2212", "admin");
        SessionManager.setCurrentUser(user);
        assertEquals("admin", user.getUsername());
    }
}


