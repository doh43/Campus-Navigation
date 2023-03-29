package org.example;

import javax.swing.*;
import java.awt.*;

public class PoiPopup extends JDialog  {
//
//    //labels for labelPanel
//    private JLabel poiName;
//    private JLabel poiType;
//    private JLabel poiDescription;
//
//    //buttons for buttonPanel
//    private JButton favoriteButton;
//    private JButton editButton;
//    private JButton deleteButton;
//
//
//    //panels for the dialog
//    JPanel labelPanel = new JPanel();       //handled by initPopupDialog()
//    JPanel buttonPanel = new JPanel();      //control statements based on Poi.user
//
//
//
//    /** grab correct map frame */
//    /** grab POI and its components correctly */
//    public PoiPopup(Map mapFrame, Poi selectedPoi) {
//
//        super(mapFrame, selectedPoi.getName(), true);
//        /* DIALOG TITLE
//         *      frame - make sure the map screen frame is being passed
//         *      title - String - call getter method for POIs name, set as dialog title
//         *      modal - true - can't interact with map frame unless dialog is exited
//         *
//         */
//        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
//        //set up the label panel with POI description/name/type
//        initPopupDialog(selectedPoi);
//
//
//        /*  SELECT favoriteOnly or edit Dialog to display
//         *      BASED ON
//         */
//        if(selectedPoi.user)
//            if(selectedPoi.builtIn) favoriteOnlyDialog();
//            else editDialog();
//        else
//            editDialog();
//
//
//
//
//        this.add(labelPanel);
//        this.add(buttonPanel);
//        this.setLayout(new GridLayout(2,1));
//
//
//        pack();
//        this.setSize(300,200);
//        this.setLocationRelativeTo(mapFrame);
//    }
//
//    /** initPopupDialog()
//     * <p>
//     *      using the POI that's clicked
//     *
//     * @param selectedPoi to grab its descriptions
//     */
//    private void initPopupDialog(Poi selectedPoi) {
//
//        //make sure getter methods are in place for POIs name, type, description
//        poiDescription = new JLabel("  Name: " + selectedPoi.getName());
//        poiName = new JLabel("  Type: " + selectedPoi.getType());
//        poiType = new JLabel("  Description: " + selectedPoi.getDescription());
//
//        poiDescription.setFont(new java.awt.Font("Segoe UI", 0, 12));
//        poiName.setFont(new java.awt.Font("Segoe UI", 0, 12));
//        poiType.setFont(new java.awt.Font("Segoe UI", 0, 12));
//
//
//        labelPanel.add(poiDescription);
//        labelPanel.add(poiName);
//        labelPanel.add(poiType);
//        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
//    }
//
//    /** userFavoriteDialog() for button panel POI.user
//     *
//     *      displays: [favorite]
//     *      for:
//     *          - ONLY BUILT-IN poi's for USER
//     */
//    private void favoriteOnlyDialog(){
//        favoriteButton = new JButton("Favorite");
//        buttonPanel.add(favoriteButton);
//        buttonPanel.setLayout(new FlowLayout());
//
//    }
//
//    /** editDialog() for button panel based on POI.user
//     * <p>
//     *      displays: [favorite] [edit] [delete]
//     *      for:
//     *          - ALL BUILT-IN poi's for DEVELOPER
//     *          - USER-CREATED poi's for USER
//     */
//    private void editDialog(){
//        favoriteButton = new JButton("Favorite");
//        editButton = new JButton("Edit");
//        deleteButton = new JButton("Delete");
//
//
//        buttonPanel.add(favoriteButton);
//        buttonPanel.add(editButton);
//        buttonPanel.add(deleteButton);
//        buttonPanel.setLayout(new FlowLayout());
//
//    }
//
//
}
