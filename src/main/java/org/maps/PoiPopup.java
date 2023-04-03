package org.maps;

import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;

public class PoiPopup extends JDialog  {

    //labels for labelPanel
    private JLabel poiType;
    private JLabel poiRoomNum;
    private JLabel poiDescription;

    //buttons for buttonPanel
    private JButton favoriteButton;
    private JButton editButton;
    private JButton deleteButton;


    //panels for the dialog
    JPanel labelPanel = new JPanel();       //handled by initPopupDialog()
    JPanel buttonPanel = new JPanel();      //control statements based on Poi.user



    /** grab correct map frame */
    /** grab POI and its components correctly */
    PoiPopup(Poi selectedPoi) {
        super(Maps.getMapFrame(), selectedPoi.getName(), true);
        /* DIALOG TITLE
         *      frame - make sure the map screen frame is being passed
         *      title - String - call getter method for POIs name, set as dialog title
         *      modal - true - can't interact with map frame unless dialog is exited
         *
         */
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
        mousePosRelativeToViewport.x += 40;
        mousePosRelativeToViewport.y -= 140;


        this.add(labelPanel);
        this.add(buttonPanel);
        this.setLayout(new GridLayout(2,1));


        pack();
        this.setSize(300,200);
        this.setResizable(false);
//        this.setLocationRelativeTo(Maps.getMapFrame());
        this.setLocation(mousePosRelativeToViewport);
    }

    /** initPopupDialog()
     * <p>
     *      using the POI that's clicked
     *
     * @param selectedPoi to grab its descriptions
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

    /** userFavoriteDialog() for button panel POI.user
     *
     *      displays: [favorite]
     *      for:
     *          - ONLY BUILT-IN poi's for USER
     */
    private void favoriteOnlyDialog(){
        favoriteButton = new JButton("Favorite");
        buttonPanel.add(favoriteButton);
        buttonPanel.setLayout(new FlowLayout());

    }

    /** editDialog() for button panel based on POI.user
     * <p>
     *      displays: [favorite] [edit] [delete]
     *      for:
     *          - ALL BUILT-IN poi's for DEVELOPER
     *          - USER-CREATED poi's for USER
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
