package org.example;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
public class PoiPanel extends JPanel implements ActionListener, EditTool, MouseListener {
    JButton button;
    JButton submit;
    JTextField poiName;
    JTextField poiId;
    JComboBox<String> poiType;
    JTextField poiRoomNum;
    JTextField poiDesc;
    JButton poiPos;
    JLabel poiPosLabel;
    Boolean posMode;
    Point mousePosAbsolute;

    Data d;

    int panelWidth = 200;

    PoiPanel() {
        d = Data.getInstance();

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

        JLabel poiIdLabel = new JLabel("Id");
        poiId = new JTextField();

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

        poiIdLabel.setBounds(5,80,panelWidth-10,20);
        poiId.setBounds(5,100, panelWidth-10, 40);

        poiTypeLabel.setBounds(5,150,panelWidth-10,20);
        poiType.setBounds(5,170, panelWidth-10, 40);

        poiRoomNumLabel.setBounds(5,220,panelWidth-10,20);
        poiRoomNum.setBounds(5,240, panelWidth-10, 40);

        poiDescLabel.setBounds(5,290,panelWidth-10,20);
        poiDesc.setBounds(5,310, panelWidth-10, 40);

        poiPos.setBounds(5,360, panelWidth - 10, 30);
        poiPos.setFocusable(false);
        poiPos.addActionListener(this);
        poiPosLabel.setBounds(5,390, panelWidth - 10, 20);

        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBounds(5, 420, panelWidth - 10,40);
        submit.addActionListener(this);

        this.add(button);
        this.add(poiNameLabel);
        this.add(poiName);
        this.add(poiIdLabel);
        this.add(poiId);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("ADD")) {
                this.setBounds(0, 0, panelWidth, 1000);
                button.setBounds(0,605, panelWidth,200);
                button.setText("CLOSE");
                SidePanel.disableSelection();
            } else {
                this.setBounds(0,605,panelWidth,200);
                button.setBounds(0,0, panelWidth,200);
                button.setText("ADD");
                SidePanel.enableSelection();
            }
        } else if (e.getSource() == submit) {
            Poi p = new Poi(
                    poiName.getText(),
                    poiType.getSelectedItem().toString(),
                    Integer.parseInt(poiId.getText()),
                    Integer.parseInt(poiRoomNum.getText()),
                    poiDesc.getText(),
                    "",
                    mousePosAbsolute.x,
                    mousePosAbsolute.y
            );
            addPoi(Maps.getBuildingCode(),MapPanel.getFloorNum(),p.convertJSON());
        } else if (e.getSource() == poiPos) {
            if (!posMode) {
                posMode = true;
                poiPos.setText("Click on Map");
                MapPanel.getMapScroll().addMouseListener(this);
            } else {
                poiPos.setText("Set Position");
                posMode = false;
                MapPanel.getMapScroll().removeMouseListener(this);
            }
        }
    }

    @Override
    public void addPoi(String building, int floorNum, JSONObject o) {
        d.getPois(building, floorNum).put(o);
        d.storeData(d.savedData);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

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