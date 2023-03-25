package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SidePanel extends JPanel implements ActionListener {
    JButton button;
    JPanel poiList;
    JPanel layer;
    JPanel favourites;
    JPanel poiPanel;
    SidePanel() {
        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(200,1000));

        poiList = new PoiListPanel();
        layer = new LayerPanel();
        favourites = new FavouritesPanel();
        poiPanel = new PoiPanel();


        button = new JButton();
        button.setText("ADD");
        button.setFocusable(false);
        button.addActionListener(this);

        add(favourites);
        add(layer);
        add(poiList);
        add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("ADD")) {
                setLayout(new BorderLayout());
                remove(button);
                remove(favourites);
                remove(layer);
                remove(poiList);
                add(poiPanel, BorderLayout.NORTH);
                poiPanel.setPreferredSize(new Dimension(200, 605));
                add(button, BorderLayout.SOUTH);
                button.setPreferredSize(new Dimension(200, 200));
                poiPanel.setVisible(true);
                button.setText("CLOSE");
            } else {
                setLayout(new GridLayout(4, 1));
                poiPanel.setVisible(false);
                remove(poiPanel);
                remove(button);
                add(favourites);
                add(layer);
                add(poiList);
                add(button);
                button.setText("ADD");
            }

        }
    }
}
