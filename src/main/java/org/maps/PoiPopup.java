package org.maps;

import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** This class is responsible for adding a popup that displays POI information when it (the POI) is selected by the user.
 * @author Ethan Wakefield, Aryan Saxena, Taejun Ha, Tomas Garcia
 * @version 1.0 */
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
     * Constructor for the PoiPopup class.
     * Sets up the dialog box,
     * @param selectedPoi - the POI that was clicked on. */
    PoiPopup(Poi selectedPoi, JButton selectedButton) {
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
        if(!SessionManager.getCurrentUser().getUsername().equals("admin")) {
            if(selectedPoi.getBuiltIn()) {
                favoriteOnlyDialog(selectedPoi);
            } else {
                editDialog(selectedPoi);
            }
        } else {
            editDialog(selectedPoi);
        }

        Point mousePosRelativeToViewport = MapPanel.getMapScroll().getMousePosition();

        this.add(labelPanel);
        this.add(buttonPanel);
        this.setLayout(new GridLayout(2,1));

        pack();
        this.setSize(300,300);
        this.setResizable(false);

        if (mousePosRelativeToViewport == null) {
            this.setLocationRelativeTo(Maps.getMapFrame());
        } else {
            mousePosRelativeToViewport.x += 40;
            mousePosRelativeToViewport.y -= 140;
            this.setLocation(mousePosRelativeToViewport);
        }

        Color holdButtonColor = selectedButton.getBackground();
        selectedButton.setBackground(Color.BLACK);

        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                selectedButton.setBackground(holdButtonColor);
            }
        });
    }

    /** initPopupDialog()
     * Sets up the label panel with POI description/name/type.
     * @param selectedPoi - the POI that was clicked on. */
    private void initPopupDialog(Poi selectedPoi) {

        //make sure getter methods are in place for POIs name, type, description
        poiType = new JLabel("<html>  <b>Type:</b> " + selectedPoi.getType());
        poiRoomNum = new JLabel("<html>  <b>Room Number:</b> " + selectedPoi.getRoomNum());
        poiDescription = new JLabel("<html>  <b>Description:</b> " + selectedPoi.getDesc());
        poiType.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        poiRoomNum.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        poiDescription.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        poiDescription.setSize(poiDescription.getPreferredSize().width, poiDescription.getPreferredSize().height+10);

        // Add padding
        labelPanel.setBorder(BorderFactory.createEmptyBorder(5,10,0,10));
        labelPanel.setSize(labelPanel.getPreferredSize().width, labelPanel.getPreferredSize().height+30);
        labelPanel.add(poiType);
        labelPanel.add(poiRoomNum);
        labelPanel.add(poiDescription);

//        labelPanel.setLayout(new GridLayout(3,1));      //TODO try changing this to BoxLayout
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
    }

    /** favoriteOnlyDialog()
     * Sets up the button panel with only the favorite button */
    private void favoriteOnlyDialog(Poi selectedPoi){
        favoriteButton = new JButton(isFavourited(selectedPoi) ? "Unfavourite" : "Favourite");
        buttonPanel.add(favoriteButton);
        buttonPanel.setLayout(new FlowLayout());
        favoriteButton.addActionListener(e -> {
            favouritePoi(selectedPoi);
            MapPanel.setUpTypePanels();
            this.dispose();

        });
    }

    /** editDialog()
     * Sets up the button panel with the edit and delete buttons.
     * @param selectedPoi - the POI that was clicked on. */
    private void editDialog(Poi selectedPoi){
        favoriteButton = new JButton(isFavourited(selectedPoi) ? "Unfavourite" : "Favourite");
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
        favoriteButton.addActionListener(e -> {
            favouritePoi(selectedPoi);
            MapPanel.setUpTypePanels();
            this.dispose();

        });

        buttonPanel.add(favoriteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setLayout(new FlowLayout());

    }

    /** deletePoi()
     * Deletes a POI from the data file
     * @param p - the POI to be deleted */
    public void deletePoi(Poi p) {
        Data d = Data.getInstance();
        // If the user is an admin, delete the POI from the savedData
        if (SessionManager.getCurrentUser().getUsername().equals("admin")) {
            JSONArray a = d.getPois(Maps.getBuildingCode(), MapPanel.getFloorNum());
            for (int i = 0; i < a.length(); i++) {
                if (a.getJSONObject(i).getInt("id") == p.getId()) {
                    a.remove(i);
                }
            }
            d.storeData(d.savedData);
        // if the user is not an admin, delete the POI from the userData
        } else {
            JSONArray a = d.getCustomPOIs(Maps.getBuildingCode(), MapPanel.getFloorNum());
            for (int i = 0; i < a.length(); i++) {
                if (a.getJSONObject(i).getInt("id") == p.getId()) {
                    a.remove(i);
                }
            }
            // if the poi is favourited, remove it from the favourites array
            if (isFavourited(p)) {
                JSONArray f = d.getFavourites();
                for (int i = 0; i < f.length(); i++) {
                    if (f.getInt(i) == p.getId()) {
                        f.remove(i);
                    }
                }
            }
            d.storeData(d.userData);
        }

        SidePanel.updateDropDown();         // ADDED RECENTLY TO UPDATE SIDE PANEL
        SidePanel.updateFavourites();
    }

    /** isFavourited()
     * Checks if a POI is favourited.
     * @param p - the POI to be checked.
     * @return true if the POI is favourited, false otherwise. */
    public boolean isFavourited(Poi p) {
        Data d = Data.getInstance();
        JSONArray a = d.getFavourites();
        for (int i = 0; i < a.length(); i++) {
            if (a.getInt(i) == p.getId()) {
                return true;
            }
        }
        return false;
    }

    /** favouritePoi()
     * Adds or removes a POI from the favourites array.
     * @param selectedPoi - the POI to be favourited/unfavourited. */
    public void favouritePoi(Poi selectedPoi) {
        if (SessionManager.getCurrentUser().getUsername().equals("admin")) {
            JOptionPane.showMessageDialog(null, "Admins cannot favourite POIs", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Data d = Data.getInstance();
        JSONArray a = d.getFavourites();
        if (isFavourited(selectedPoi)) {
            favoriteButton.setText("Favourite");
            for (int i = 0; i < a.length(); i++) {
                if (a.getInt(i) == selectedPoi.getId()) {
                    a.remove(i);
                }
            }
        } else {
            favoriteButton.setText("Unfavourite");
            a.put(selectedPoi.getId());
        }
        d.storeData(d.userData);
        SidePanel.updateFavourites();
    }
}
