/** Main panel that holds all other panels in the Map Page.
 * @author Tom Garcia
 * @version 2.0
 * @see org.maps.SearchPanel
 * @see org.maps.MapPanel
 * @see org.maps.BottomPanel */

package org.maps;

import javax.swing.*;
import java.awt.*;


public class MainPanel extends JPanel {
    /** Map panel that holds the map image */
    private static JPanel map;

    /** Constructor for the MainPanel class.
     * @param botPan Bottom panel that holds the back button. */
    MainPanel(BottomPanel botPan) {
        setLayout(new BorderLayout());

        map = new MapPanel();

        add(new SearchPanel(), BorderLayout.NORTH);
        add(map, BorderLayout.CENTER);
        add(botPan, BorderLayout.SOUTH);
    }
}
