package org.maps;
import org.json.JSONArray;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for creating the buttons for the bottom panel of the map viewer GUI.
 *
 * @author ewakefi, tha7, tgarci3
 */

public class BottomPanel extends JPanel implements ActionListener {
    String building;
    JButton[] floor = new JButton[10];

    /**
     * This takes in the parameter btn to create the different buttons.
     *
     * @param btn
     */
    BottomPanel(JButton btn) {
        setLayout(new BorderLayout());
        JPanel floorPanel = new JPanel();
        floorPanel.setLayout(new GridLayout());
        building = Maps.getBuildingCode();
        // Retrieves the floor attributes from the json file
        Data d = Data.getInstance();
        JSONArray jsonFloors = d.savedData.getJSONObject(building).getJSONArray("floors");
        // Creates a floor button for each floor array in the json
        for (int i = 0; i < jsonFloors.length(); i++) {
            Object floorName = d.savedData.getJSONObject(building).getJSONArray("floors").getJSONObject(i).get("name");
            floor[i] = new JButton(floorName.toString());
            floor[i].addActionListener(this);
            floorPanel.add(floor[i]);
        }
        add(floorPanel, BorderLayout.WEST);
        add(btn, BorderLayout.EAST);
    }


    /**
     * When the method is called, it retrieves the image from the maps folder and changes the current map to the new map
     *
     * @param i
     */
    public void changeFloor(int i) {
        MapPanel.setFloorNum(i);
//        MapPanel.getImageLabel().setIcon(new ImageIcon("./data/maps/"+buildingCode+"/"+buildingCode+i+".png"));
        MapPanel.setUpTypePanels();
        SidePanel.layerTracker();

    }

    /**
     * This method is responsible for making the buttons functional, whenever users click the button it will load the
     * corresponding floor map.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Data d = Data.getInstance();
        JSONArray jsonFloors = d.savedData.getJSONObject(building).getJSONArray("floors");
        // Checks which button got clicked, depending on the button clicked, it will open a new map image.
        JButton src = (JButton) e.getSource();
            for (int i = 0; i < jsonFloors.length(); i++) {
                if (src == floor[i]) {
                     // Calls the changeLabel to essentially refresh the top panel
                    SearchPanel.changeLabel(i);
                    changeFloor(i);
            }
            }
    }
}
