package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**

 PoiPanel is a JPanel used to create and submit Point of Interest (POI) objects.
 It implements ActionListener, EditTool, and MouseListener.

 @version 1.0
 @author Ethan Wakefield
 */
public class PoiPanel extends JPanel implements ActionListener, MouseListener {
    /** JButton for opening/closing the POI panel */
    private static JButton button;
    /** JButton for submitting POI information */
    JButton submit;
    /** JTextField for POI name */
    private static JTextField poiName;
    /** JComboBox for POI type */
    private static JComboBox<String> poiType;
    /** JTextField for POI room number */
    private static JTextField poiRoomNum;
    /** JTextField for POI description */
    private static JTextField poiDesc;
    /** JButton for setting POI position */
    JButton poiPos;
    /** JLabel for displaying current POI position */
    private static JLabel poiPosLabel;
    /** Boolean to indicate whether in position setting mode */
    Boolean posMode;
    /** Point object to store current mouse position */
    private static Point mousePosAbsolute;
    /** Data object to interact with application data */
    Data d;
    /** Width of the POI panel */
    int panelWidth = 200;
    /** Boolean to indicate whether the user is in editMode */
    private static boolean editMode;
    /** Integer to representing the id of the current Poi*/
    private static int poiId;
    /**
     Constructor for PoiPanel. Initializes variables and creates UI elements.
     */
    PoiPanel() {
        // Grabs building data
        d = Data.getInstance();
        // Sets editMode to false
        editMode = false;

        this.setBounds(0,605,panelWidth,200);
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        mousePosAbsolute = new Point(0,0);
        posMode = false;

        button = new JButton();
        button.setText("ADD");
        button.setFocusable(false);
        button.setBounds(0,0,panelWidth,200);
        button.addActionListener(this);

        JLabel poiNameLabel = new JLabel("Name");
        poiName = new JTextField();

        JLabel poiRoomNumLabel = new JLabel("Room Number");
        poiRoomNum = new JTextField();

        JLabel poiDescLabel = new JLabel("Description");
        poiDesc = new JTextField();

        JLabel poiTypeLabel = new JLabel("Types");
        String[] choices = {"Classroom", "Navigation", "Washroom", "Entry / Exit", "Restaurant", "Lab", "Collaborative Room"};
        poiType = new JComboBox<>(choices);

        poiPosLabel = new JLabel("Current Pos: 0,0");
        poiPos = new JButton("Set Position");

        poiNameLabel.setBounds(5,10,panelWidth-10, 20);
        poiName.setBounds(5,30, panelWidth-10, 40);

        poiTypeLabel.setBounds(5,80,panelWidth-10,20);
        poiType.setBounds(5,100, panelWidth-10, 40);

        poiRoomNumLabel.setBounds(5,150,panelWidth-10,20);
        poiRoomNum.setBounds(5,170, panelWidth-10, 40);

        poiDescLabel.setBounds(5,220,panelWidth-10,20);
        poiDesc.setBounds(5,240, panelWidth-10, 40);

        poiPos.setBounds(5,290, panelWidth - 10, 40);
        poiPos.setFocusable(false);
        poiPos.addActionListener(this);
        poiPosLabel.setBounds(5,330, panelWidth - 10, 20);

        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBounds(5, 420, panelWidth - 10,40);
        submit.addActionListener(this);

        this.add(button);
        this.add(poiNameLabel);
        this.add(poiName);
        this.add(poiTypeLabel);
        this.add(poiType);
        this.add(poiRoomNumLabel);
        this.add(poiRoomNum);
        this.add(poiDescLabel);
        this.add(poiDesc);
        this.add(submit);
        this.add(poiPos);
        this.add(poiPosLabel);
    }
    public static JButton getButton() {
        return button;
    }
    public static JTextField getPoiName() {
        return poiName;
    }
    public static JComboBox<String> getPoiType() {
        return poiType;
    }
    public static JTextField getPoiRoomNum() {
        return poiRoomNum;
    }
    public static JTextField getPoiDesc() {
        return poiDesc;
    }
    public static JLabel getPoiPosLabel() {
        return poiPosLabel;
    }
    public static Point getMousePosAbsolute() {
        return mousePosAbsolute;
    }
    public static void enterEditMode() {
        editMode = true;
    }
    public static void setPoiId(int i) {
        poiId = i;
    }
    public void resetForm() {
        editMode = false;
        poiName.setText("");
        poiType.setSelectedIndex(0);
        poiRoomNum.setText("");
        poiDesc.setText("");
        mousePosAbsolute.x = 0;
        mousePosAbsolute.y = 0;
        poiPosLabel.setText("Current Pos: 0,0");
    }
    public boolean checkFormUsed() {
        if (!poiName.getText().equals("") || !poiRoomNum.getText().equals("") || !poiDesc.getText().equals("") || mousePosAbsolute.x != 0 || mousePosAbsolute.y != 0) {
            return true;
        }
        return false;
    }
    /**

     This method is called when the user performs an action on a component that
     has registered this class as a listener. The method handles the action events
     generated by the buttons and updates the user interface accordingly.
     @param e the action event that triggered the method
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("ADD")) {
                this.setBounds(0, 0, panelWidth, 1000);
                button.setBounds(0,605, panelWidth,200);
                button.setText("CLOSE");
                SidePanel.disableSelection();
            } else if (checkFormUsed()) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Remove Changes", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    this.setBounds(0, 605, panelWidth, 200);
                    button.setBounds(0, 0, panelWidth, 200);
                    button.setText("ADD");
                    // Reset editMode when closing the panel
                    editMode = false;
                    resetForm();
                    SidePanel.enableSelection();
                }
            } else {
                this.setBounds(0,605,panelWidth,200);
                button.setBounds(0,0, panelWidth,200);
                button.setText("ADD");
                // Reset editMode when closing the panel
                editMode = false;
                resetForm();
                SidePanel.enableSelection();
            }
        } else if (e.getSource() == submit) {
            // If not editing, generate a new id
            if (!editMode)  poiId = getAvailableId(Maps.getBuildingCode(), MapPanel.getFloorNum());
            Poi p = new Poi(
                    poiName.getText(),
                    poiType.getSelectedItem().toString(),
                    poiId,
                    Integer.parseInt(poiRoomNum.getText()),
                    poiDesc.getText(),
                    "",
                    mousePosAbsolute.x,
                    mousePosAbsolute.y
            );
            // If not editing, addPoi
            if (!editMode) addPoi(Maps.getBuildingCode(),MapPanel.getFloorNum(),p.convertJSON());
            // If editing, editPoi
            if (editMode) editPoi(Maps.getBuildingCode(), MapPanel.getFloorNum(), p);
            MapPanel.setUpTypePanels();
            resetForm();
        } else if (e.getSource() == poiPos) {
            // Toggle on posMode (disable all other inputs)
            if (!posMode) {
                posMode = true;
                poiName.setEnabled(false);
                poiType.setEnabled(false);
                poiRoomNum.setEnabled(false);
                poiDesc.setEnabled(false);
                submit.setEnabled(false);
                button.setEnabled(false);
                poiPos.setText("Click on Map");
                MapPanel.getMapScroll().addMouseListener(this);
            // Toggle off posMode (enable al other inputs)
            } else {
                poiPos.setText("Set Position");
                posMode = false;
                poiName.setEnabled(true);
                poiType.setEnabled(true);
                poiRoomNum.setEnabled(true);
                poiDesc.setEnabled(true);
                submit.setEnabled(true);
                button.setEnabled(true);
                MapPanel.getMapScroll().removeMouseListener(this);
            }
        }
    }
    public int getAvailableId(String building, int floorNum) {
        JSONArray a = d.getPois(building, floorNum);
        if (a.length() == 0) return 0;
        return (Integer) a.getJSONObject(a.length() - 1).get("id") + 1;
    }
    /**

     Adds a point of interest (POI) to the system's data store for the given building and floor number.
     @param building the code for the building where the POI is located
     @param floorNum the number of the floor where the POI is located
     @param o the JSON object representing the POI to add
     */
    public void addPoi(String building, int floorNum, JSONObject o) {
        d.getPois(building, floorNum).put(o);
        d.storeData(d.savedData);
    }
    public void editPoi(String building, int floorNum, Poi p) {
        JSONArray a = d.getPois(building, floorNum);
        for (int i = 0; i < a.length(); i++) {
            // Find poi matching the id and edit fields
            if (a.getJSONObject(i).getInt("id") == p.getId()) {
                a.getJSONObject(i).put("name", p.getName());
                a.getJSONObject(i).put("type", p.getType());
                a.getJSONObject(i).put("roomNum", p.getRoomNum());
                a.getJSONObject(i).put("desc", p.getDesc());
                a.getJSONObject(i).put("posX", p.getPosX());
                a.getJSONObject(i).put("posY", p.getPosY());
            }
        }
        // Save the data to the json file
        d.storeData(d.savedData);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**

     Invoked when the mouse button has been released on a component.
     If the posMode flag is set to true, this method updates the mouse position relative to the map viewport
     and sets the corresponding absolute position to the mousePosAbsolute field.
     It also updates the text of poiPosLabel to show the current position.
     @param e MouseEvent representing the mouse release action
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (posMode) {
            Point mousePosRelativeToViewport = MapPanel.getMapScroll().getMousePosition();
            Point viewportLocation = MapPanel.getMapScroll().getViewport().getViewPosition();
            mousePosAbsolute.setLocation(mousePosRelativeToViewport.x + viewportLocation.x, mousePosRelativeToViewport.y + viewportLocation.y);
            poiPosLabel.setText("Current Pos: " + mousePosAbsolute.x + "," + mousePosAbsolute.y);
            System.out.println(mousePosAbsolute);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}