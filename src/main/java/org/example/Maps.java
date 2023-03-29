/** Displays a map page for a specific building, including all floors and associated POIs. A side panel and search panel
 * are used to access/modify map data.
 * @author Tomas Garcia, Ethan Tiger Wakefield, Taejun Ha
 * @version 1.0
 * @see org.example.Building
 * @see org.example.PoiPanel
 * @see org.example.SidePanel
 * @see org.example.BottomPanel
 * @see org.example.SearchPanel */
package org.example;
import javax.swing.*;
import java.awt.*;

public class Maps extends JFrame {
    private static JFrame frame;
    /* Used to access the specific building in the buildings JSON file */
    private static String buildingCode;

    /* The building being displayed */
    private static Building mapBuilding;
    Maps(String choice) {
        frame = this;
        this.buildingCode = choice;
        buildingCode = choice;
        mapBuilding = new Building(buildingCode);
        setTitle("Western Campus Navigation - Map Page");
        setSize(1400,820);
        setLayout(new BorderLayout());


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Must be in Maps because of the dispose method
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            if (e.getSource() == back) {
                dispose();
                new LandingPage();

            }
        });

        BottomPanel botPan = new BottomPanel(back);

        add(new SidePanel(), BorderLayout.WEST);
        add(new MainPanel(botPan), BorderLayout.CENTER);
//        pack();
        setVisible(true);

    }

    public static String getBuildingCode() {
        return buildingCode;
    }
    public static JFrame getMapFrame() {
        return frame;
    }

    public static Building getMapBuilding() {
        return mapBuilding;
    }

    /** Used to test the Maps class
     * @ //TODO: delete after testing */
    public static void main(String[] args) {

        new Maps("mc");
    }
}
