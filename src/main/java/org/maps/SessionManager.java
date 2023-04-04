package org.maps;

/** Responsible for storing the current user logged into the program.
 * @author Daniel Oh
 * @version 1.0
 */
public class SessionManager {
    private static User currentUser;
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
}
