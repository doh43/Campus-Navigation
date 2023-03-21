package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PoiPanel extends JPanel implements ActionListener {
    JButton button;
    JTextField poiName;
    PoiPanel() {
        this.setBounds(0,375,150,100);
        this.setBackground(Color.GRAY);
        this.setLayout(null);
        button = new JButton();
        button.setText("ADD");
        button.setFocusable(false);
        button.setBounds(0,0,150,100);
        button.addActionListener(this);

        this.add(button);
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
        }
    }
}
