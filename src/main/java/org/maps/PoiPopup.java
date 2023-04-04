package org.maps;

import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for creating the search bar and the corresponding building/floor labels so the user knows
 * which building and floor they are in when they open the map.
 *
 * @version 1.0
 * @author Ethan Wakefield, Taejun Ha, Tomas Garcia, Aryan Saxena
 */
public class PoiPopup extends JDialog  {
    /** JLabel for displaying the type of POI */
    private JLabel poiType;
    /** JLabel for displaying the room number of the POI */
    private JLabel poiRoomNum;
    /** JLabel for displaying the description of the POI */
    private JLabel poiDescription;

    /** JButton for favoriting a POI */
    private JButton favoriteButton;
    /** JButton for editing a POI */
    private JButton editButton;
    /** JButton for deleting a POI */
    private JButton deleteButton;


    /** JPanel for holding the label components */
    JPanel labelPanel = new JPanel();       //handled by initPopupDialog()
    /** JPanel for holding the button components */
    JPanel buttonPanel = new JPanel();      //control statements based on Poi.user



    /** PoiPopup()
     * constructor for the PoiPopup class
     * sets up the dialog box
     *
     * @param selectedPoi - the POI that was clicked on
     */
    PoiPopup(Poi selectedPoi) {
        super(Maps.getMapFrame(), selectedPoi.getName(), true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //set up the label panel with POI description/name/type
        initPopupDialog(selectedPoi);


        /*  SELECT favoriteOnly or edit Dialog to display
         *      BASED ON
         */
        if(false)
            if(false) favoriteOnlyDialog();
            else editDialog(selectedPoi);
        else
            editDialog(selectedPoi);


        Point mousePosRelativeToViewport = MapPanel.getMapScroll().getMousePosition();



        this.add(labelPanel);
        this.add(buttonPanel);
        this.setLayout(new GridLayout(2,1));


        pack();
        this.setSize(300,200);
        this.setResizable(false);

        if (mousePosRelativeToViewport == null) {
            this.setLocationRelativeTo(Maps.getMapFrame());
        } else {
            mousePosRelativeToViewport.x += 40;
            mousePosRelativeToViewport.y -= 140;
            this.setLocation(mousePosRelativeToViewport);
        }
    }

    /** initPopupDialog()
     * sets up the label panel with POI description/name/type
     *
     * @param selectedPoi - the POI that was clicked on
     */
    private void initPopupDialog(Poi selectedPoi) {

        //make sure getter methods are in place for POIs name, type, description
        poiType = new JLabel("<html>  <b>Type:</b> " + selectedPoi.getType());
        poiRoomNum = new JLabel("<html>  <b>Room Number:</b> " + selectedPoi.getRoomNum());
        poiDescription = new JLabel("<html>  <b>Description:</b> " + selectedPoi.getDesc());
        poiType.setFont(new java.awt.Font("Segoe UI", 0, 12));
        poiRoomNum.setFont(new java.awt.Font("Segoe UI", 0, 12));
        poiDescription.setFont(new java.awt.Font("Segoe UI", 0, 12));

        // Add padding
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        labelPanel.add(poiType);
        labelPanel.add(poiRoomNum);
        labelPanel.add(poiDescription);


        labelPanel.setLayout(new GridLayout(3,1));
    }

    /** favoriteOnlyDialog()
     * sets up the button panel with only the favorite button
     */
    private void favoriteOnlyDialog(){
        favoriteButton = new JButton("Favorite");
        buttonPanel.add(favoriteButton);
        buttonPanel.setLayout(new FlowLayout());

    }

    /** editDialog()
     * sets up the button panel with the edit and delete buttons
     *
     * @param selectedPoi - the POI that was clicked on
     */
    private void editDialog(Poi selectedPoi){
        favoriteButton = new JButton("Favorite");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        editButton.addActionListener(e -> {
            // Open Add Panel
            if (PoiPanel.getButton().getText().equals("ADD")) {
                PoiPanel.getButton().doClick();
            }
            // Pass values of selected Poi to input fields
            PoiPanel.getPoiName().setText(selectedPoi.getName());
            PoiPanel.getPoiType().setSelectedItem(selectedPoi.getType());
            PoiPanel.getPoiRoomNum().setText(String.valueOf(selectedPoi.getRoomNum()));
            PoiPanel.getPoiDesc().setText(selectedPoi.getDesc());
            PoiPanel.getMousePosAbsolute().x = selectedPoi.getPosX();
            PoiPanel.getMousePosAbsolute().y = selectedPoi.getPosY();
            PoiPanel.getPoiPosLabel().setText("Current Pos: " + selectedPoi.getPosX() + "," + selectedPoi.getPosY());
            PoiPanel.setPoiId(selectedPoi.getId());
            // Set enter mode to true
            PoiPanel.enterEditMode();
            // Remove PoiPopup
            this.dispose();
        });
        deleteButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete Poi", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                deletePoi(selectedPoi);
                MapPanel.setUpTypePanels();
                this.dispose();
            }
        });

        buttonPanel.add(favoriteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setLayout(new FlowLayout());

    }
    /** deletePoi()
     * deletes a POI from the data file
     *
     * @param p - the POI to be deleted
     */
    public void deletePoi(Poi p) {
        Data d = Data.getInstance();
        JSONArray a = d.getPois(Maps.getBuildingCode(), MapPanel.getFloorNum());
        for (int i = 0; i < a.length(); i++) {
            if (a.getJSONObject(i).getInt("id") == p.getId()) {
                a.remove(i);
            }
        }
        d.storeData(d.savedData);
    }


}
