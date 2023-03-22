package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PoiPanel extends JPanel implements ActionListener {
    JButton button;
    JButton submit;
    JTextField poiName;
    JTextField poiId;
    JComboBox poiType;
    JTextField poiRoomNum;
    JTextField poiDesc;

    PoiPanel() {
        this.setBounds(0,375,150,100);
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        button = new JButton();
        button.setText("ADD");
        button.setFocusable(false);
        button.setBounds(0,0,150,100);
        button.addActionListener(this);

        poiName = new JTextField("Name");
        poiId = new JTextField("Id");
        poiRoomNum = new JTextField("Room Num");
        poiDesc = new JTextField("Desc");

        String[] choices = {"Classroom", "Navigation", "Washroom", "Entry / Exit", "Restaurant", "Lab", "Collaborative Room"};
        poiType = new JComboBox(choices);


        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBounds(5, 160, 100,20);
        submit.addActionListener(this);

        poiName.setBounds(5,10, 140, 20);
        poiId.setBounds(5,40, 140, 20);
        poiType.setBounds(5,70, 140, 20);
        poiRoomNum.setBounds(5,100, 140, 20);
        poiDesc.setBounds(5,130, 140, 20);

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
                this.setBounds(0, 0, 150, 500);
                button.setBounds(0,375, 150,100);
                button.setText("CLOSE");
            } else {
                this.setBounds(0, 375, 150, 100);
                button.setBounds(0,0, 150,100);
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
            System.out.println(p.convertJSON());
        }
    }
}
