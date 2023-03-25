package org.example;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JTextField searchPrompt = new JTextField("Search for POIs");
        searchPrompt.setPreferredSize(new Dimension(190,25));

        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(25,25));
        searchButton.addActionListener(e -> {
            // Saves the user input (needs to be used for search NOT IMPLEMENTED YET)
            String s1 = searchPrompt.getText();
        });

        add(searchButton);
        add(searchPrompt);
    }
}
