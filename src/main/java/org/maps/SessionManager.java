/** Responsible for storing the current user logged into the program.
 * @author Daniel Oh
 * @version 1.0
 */

package org.maps;

public class SessionManager {
    /** Specifies the current user of the application */
    private static User currentUser;
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
}
