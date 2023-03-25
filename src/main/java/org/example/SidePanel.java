package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener {
    private SpringLayout layout;
    private PoiListPanel poiPan;
    private LayerPanel layerPan;
    private FavouritesPanel favPan;
    private PoiPanel addPoiPan;
    private JButton addBtn;

    SidePanel() {
        layout = new SpringLayout();
        setPreferredSize(new Dimension(200,1000));
        setLayout(layout);

        poiPan = new PoiListPanel();
        layerPan = new LayerPanel();
        favPan = new FavouritesPanel();
        addPoiPan = new PoiPanel();

        addBtn = new JButton("+");
        addBtn.setFocusable(false);
        addBtn.addActionListener(this);

        setDefaultLayout();

        add(poiPan);
        add(layerPan);
        add(favPan);
        add(addBtn);
    }

    /* Sets the default layout */
    private void setDefaultLayout() {
        layout.putConstraint(SpringLayout.WEST, poiPan, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, poiPan, 0, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, layerPan, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, layerPan, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, layerPan, 150, SpringLayout.SOUTH, poiPan);

        layout.putConstraint(SpringLayout.WEST, favPan, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, favPan, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, favPan, 5, SpringLayout.SOUTH, layerPan);

        layout.putConstraint(SpringLayout.WEST, addBtn, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, addBtn, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 150, SpringLayout.SOUTH, favPan);
        layout.putConstraint(SpringLayout.SOUTH, addBtn, 0, SpringLayout.SOUTH, this);

    }

    /* Sets the layout after the addPOI button is pressed */
    private void setUpdateLayout() {
        layout.putConstraint(SpringLayout.WEST, addPoiPan, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, addPoiPan, 0, SpringLayout.EAST, this);

        layout.putConstraint(SpringLayout.WEST, addBtn, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, addBtn, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, addBtn, 0, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 570, SpringLayout.SOUTH, addPoiPan);
    }

    /* Method that changes the panel depending on the add poi button. When shifting to the POI panel the button shows
    * but the POI panel doesn't. When it returns to the default layout everything works perfectly. TODO: Fix method */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (addBtn.getText().equals("+")) {
            remove(addBtn);
            remove(poiPan);
            remove(layerPan);
            remove(favPan);

            setUpdateLayout();

            add(addPoiPan);
            add(addBtn);
            addPoiPan.setVisible(true);
            addBtn.setText("CLOSE");
        }
        else if (addBtn.getText().equals("CLOSE")) {
            addPoiPan.setVisible(false);
            remove(addPoiPan);
            remove(addBtn);

            setDefaultLayout();

            add(poiPan);
            add(layerPan);
            add(favPan);
            add(addBtn);
            addBtn.setText("+");
        }
    }
}
