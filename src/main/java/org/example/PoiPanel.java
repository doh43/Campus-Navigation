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

        poiName = new JTextField("Name");
        poiId = new JTextField("Id");
        poiRoomNum = new JTextField("Room Num");
        poiDesc = new JTextField("Desc");

        String[] choices = {"Classroom", "Navigation", "Washroom", "Entry / Exit", "Restaurant", "Lab", "Collaborative Room"};
        poiType = new JComboBox<>(choices);


        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBounds(5, 160, 100,20);
        submit.addActionListener(this);

        poiName.setBounds(5,10, panelWidth-10, 20);
        poiId.setBounds(5,40, panelWidth-10, 20);
        poiType.setBounds(5,70, panelWidth-10, 20);
        poiRoomNum.setBounds(5,100, panelWidth-10, 20);
        poiDesc.setBounds(5,130, panelWidth-10, 20);

        this.add(button);
        this.add(poiName);
        this.add(poiId);
        this.add(poiType);
        this.add(poiRoomNum);
        this.add(poiDesc);
        this.add(submit);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("ADD")) {
                this.setBounds(0, 0, panelWidth, 1000);
                button.setBounds(0,605, panelWidth,200);
                button.setText("CLOSE");
            } else {
                this.setBounds(0,605,panelWidth,200);
                button.setBounds(0,0, panelWidth,200);
                button.setText("ADD");
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