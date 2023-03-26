package org.example;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PoiPanel extends JPanel implements ActionListener, EditTool {
    JButton button;
    JButton submit;
    JTextField poiName;
    JTextField poiId;
    JComboBox<String> poiType;
    JTextField poiRoomNum;
    JTextField poiDesc;

    Data d;

    int panelWidth = 200;

    PoiPanel() {
        d = Data.getInstance();
        this.setBounds(0,605,panelWidth,200);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

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

        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBounds(5, 360, panelWidth - 10,40);
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
                    0,
                    0
            );
            addPoi("mc",0,p.convertJSON());
        }
    }

    @Override
    public void addPoi(String building, int floorNum, JSONObject o) {
        d.getPois(building, floorNum).put(o);
        d.storeData(d.savedData);
    }
}