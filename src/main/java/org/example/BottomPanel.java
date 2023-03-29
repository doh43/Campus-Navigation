package org.example;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.example.Maps.buildingCode;

/**
 * This class is responsible for creating the buttons for the bottom panel (bottom of the map)
 *
 * @author ewakefi, tha7, tgarci3
 */

public class BottomPanel extends JPanel implements ActionListener {
    static Object newFloor;
    JButton floor;

    /**
     * This takes in the parameter btn to create the different buttons.
     *
     * @param btn
     */
    BottomPanel(JButton btn) {
        setLayout(new BorderLayout());
        JPanel floorPanel = new JPanel();
        floorPanel.setLayout(new GridLayout());

        // Retrieves the floor attributes from the json file
        Data d = Data.getInstance();
        JSONArray jsonFloors = d.savedData.getJSONObject(buildingCode).getJSONArray("floors");

        // Creates a floor button for each floor array in the json
        for (int i = 0; i < jsonFloors.length(); i++) {
            // newFloor = d.savedData.getJSONObject(buildingCode).getJSONArray("floors").getJSONObject(i).get("id"); // Remove later
            Object floorName = d.savedData.getJSONObject(buildingCode).getJSONArray("floors").getJSONObject(i).get("name");
            floor = new JButton(floorName.toString());
            floor.addActionListener(this);
            floorPanel.add(floor);
        }
        add(floorPanel, BorderLayout.WEST);
        add(btn, BorderLayout.EAST);
    }

    /**
     * This method retrieves the image for the floor map.
     *
     * @param i
     */
    public void changeFloor(int i) {
        MapPanel.setFloorNum(i);
        MapPanel.getImageLabel().setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+i+".png"));
    }

    /**
     * This method is responsible for making the buttons function, whenever users click the button it will load the
     * corresponding floor map.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == floor) {
//            MapPanel.getImageLabel().setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+i".png"));
//        }
    }
}




