/** Displays a map page for a specific building, including all floors and associated POIs. A side panel and search panel
 * are used to access/modify map data.
 * @author Tomas Garcia, Ethan Tiger Wakefield, Taejun Ha
 * @version 1.0
 * @see org.maps.Building
 * @see org.maps.PoiPanel
 * @see org.maps.SidePanel
 * @see org.maps.BottomPanel
 * @see org.maps.SearchPanel */
package org.maps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Maps extends JFrame {
    private static JFrame frame;
    /* Used to access the specific building in the buildings JSON file */
    private static String buildingCode;

    /* The building being displayed */
    private static Building mapBuilding;
    Maps(String choice) {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame = this;
        buildingCode = choice;
        mapBuilding = new Building(buildingCode);
        setTitle("Western Campus Navigation - Map Page");
        setSize(1400,820);
        setLayout(new BorderLayout());

        // If the user is a developer, the title of the panel will change to indicate it
        if(SessionManager.getCurrentUser().getUsername().equals("admin")) {
            this.setTitle("Western Campus Navigation (DEVELOPER MODE) - Map Page");
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Must be in Maps because of the dispose method
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            if (e.getSource() == back) {

                int option = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to close this window?",
                        "Confirm Close",
                        JOptionPane.YES_NO_OPTION
                );

                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the window
                    new LandingPage();

                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });

        BottomPanel botPan = new BottomPanel(back);

        add(new MainPanel(botPan), BorderLayout.CENTER);
        add(new SidePanel(), BorderLayout.WEST);

//        pack();
        setVisible(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to close this window?",
                        "Confirm Close",
                        JOptionPane.YES_NO_OPTION
                );
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); // Close the window
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });


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
}
