package org.maps;

import javax.swing.*;
import java.awt.*;

/**
 * Main panel that holds all the other panels.
 * @author Tom Garcia
 * @version 2.0
 */
public class MainPanel extends JPanel {
    /** Map panel that holds the map image */
    private static JPanel map;

    /**
     * Constructor for the MainPanel class.
     * @param botPan Bottom panel that holds the back button.
     */
    MainPanel(BottomPanel botPan) {
        setLayout(new BorderLayout());


        map = new MapPanel();


        add(new SearchPanel(), BorderLayout.NORTH);
        add(map, BorderLayout.CENTER);
        add(botPan, BorderLayout.SOUTH);

    }

}
