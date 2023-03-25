package org.example;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PoiPanel extends JPanel implements ActionListener, EditTool {
    JButton submit;
    JTextField poiName;
    JComboBox poiType;
    JTextField poiRoomNum;
    JTextField poiDesc;

    Data d;

    int panelWidth = 200;

    PoiPanel() {
        d = Data.getInstance();
        this.setLayout(null);
        this.setBackground(Color.GRAY);

        poiName = new JTextField("Name");
        poiRoomNum = new JTextField("Room Num");
        poiDesc = new JTextField("Desc");

        String[] choices = {"Classroom", "Navigation", "Washroom", "Entry / Exit", "Restaurant", "Lab", "Collaborative Room"};
        poiType = new JComboBox(choices);


        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.addActionListener(this);

        poiName.setBounds(5,10, panelWidth-10, 20);
        poiType.setBounds(5,40, panelWidth-10, 20);
        poiRoomNum.setBounds(5,70, panelWidth-10, 20);
        poiDesc.setBounds(5,100, panelWidth-10, 20);

        this.add(poiName);
        this.add(poiType);
        this.add(poiRoomNum);
        this.add(poiDesc);
        this.add(submit);
    }
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == submit) {
            Poi p = new Poi(
                    poiName.getText(),
                    poiType.getSelectedItem().toString(),
                    0,
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
