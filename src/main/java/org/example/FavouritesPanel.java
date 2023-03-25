package org.example;

import javax.swing.*;
import java.awt.*;

public class FavouritesPanel extends JPanel {
    FavouritesPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);

        JLabel favSelect = new JLabel("Favourites");
        String[] favs = {"fav1","fav2","fav3","fav4"};
        JComboBox<String> favBox = new JComboBox<>(favs);

        add(favSelect, BorderLayout.NORTH);
        add(favBox, BorderLayout.CENTER);
    }
}
