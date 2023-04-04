package org.maps;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Help Page - Help Guide with picture and textual guidelines on how to interact with application
 *
 * @version 1.0
 * @author Aryan Saxena
 * */

public class Help extends JFrame implements ActionListener {
    JButton back;

    /**
     * Help Page Constructor
     *
     */
    Help() {
        // SET UP HELP PAGE
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 900);
        this.setTitle("Western Campus Navigation");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // PAGE TITLE
        String HelpText = "<html> <h1>Help Guide</h1>";
        JTextPane HelpTextPane = new JTextPane();
        HelpTextPane.setContentType("text/html");
        HelpTextPane.setText(HelpText);
        HelpTextPane.setEditable(false);
        HelpTextPane.setBounds(5, 5, 950, 100);
        HelpTextPane.setFont(new java.awt.Font("Segoe UI", 0, 12));
        HelpTextPane.setBackground(this.getBackground());
        this.add(HelpTextPane);

        // BACK BUTTON
        back = new JButton("Back");
        back.setBounds(800, 750, 100, 40);
        back.addActionListener(this);
        back.setFont(new java.awt.Font("Segoe UI", 0, 12));
        this.add(back);

        // SCROLL PANE WITH ALL THE HELP GUIDE CONTENT
        JScrollPane helpScroll = new JScrollPane();
        helpScroll.setBounds(5,100, 950, 600);
        helpScroll.setLayout(new ScrollPaneLayout());
        helpScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension helpViewport = helpScroll.getViewport().getViewSize();
        this.add(helpScroll);

        JPanel tempPanel = new JPanel();
        tempPanel.setBounds(0,0, helpScroll.getPreferredSize().width, 1500);
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));
        tempPanel.setBackground(this.getBackground());
        helpScroll.setViewportView(tempPanel);

        // HELP 1
            // Landing Page Navigation
        String helpPanel1text = "The <b>Landing Page</b> presents a drop-down list of available buildings for the user to select a particular building and click 'View Map' to confirm the selection <br> " +
                "and proceed to the <b>Map Page</b> to view the building floor maps. The <b>About</b> can be accessed from this page which provides application version <br> " +
                "and developer contact information. The Back button can be used to navigate back to the <b>Login Page</b>.";
        JTextPane helpPanel1 = new JTextPane();
        helpPanel1.setContentType("text/html");
        helpPanel1.setEditorKit(new HTMLEditorKit());
        helpPanel1.setText(helpPanel1text);
        helpPanel1.setEditable(false);
        helpPanel1.setFont(new java.awt.Font("Segoe UI", 0, 12));
        helpPanel1.setBounds(0,0, helpViewport.width, 50);
        helpPanel1.setBackground(this.getBackground());
        tempPanel.add(helpPanel1);

            // SET UP HELP1 IMAGE
        JLabel helpImage1 = new JLabel();
        helpImage1.setIcon(new ImageIcon("./data/help/help5.png"));
        helpImage1.setBounds(100,0, helpViewport.width, helpImage1.getPreferredSize().height);
        tempPanel.add(helpImage1);

        // HELP 2
            // MAP PAGE FEATURES
        String helpPanel2text = "The Map Page has several features to navigate available points of interests (POIs) and floor maps. Floor selection can be done using the footer tabs [8].<br>" +
                "The left Side Panel displays a drop-down list of all available POIs for the current floor map [4], a drop-down list of the user-favorite POIs for the current <br> " +
                "floor map [6], and the POIs displayed in the floor map can be toggled by their layer type using the checkboxes [5]. The search bar [3] can also be used <br>" +
                "to search for POIs using their name. A POI displayed on the map appears as a colored box [10]. Using the back button [9] will navigate the user back to <br>" +
                " the landing page where a different building can be selected. The add button [7] can be used by developers and users to create built-in and user-created <br>" +
                "POIs, respectively. ";
        JTextPane helpPanel2 = new JTextPane();
        helpPanel2.setContentType("text/html");
        helpPanel2.setEditorKit(new HTMLEditorKit());
        helpPanel2.setText(helpPanel2text);
        helpPanel2.setEditable(false);
        helpPanel2.setFont(new java.awt.Font("Segoe UI", 0, 12));
        helpPanel2.setBounds(0,0, helpViewport.width, 50);
        helpPanel2.setBackground(this.getBackground());
        tempPanel.add(helpPanel2);

            // SET UP HELP2 IMAGE
        JLabel helpImage2 = new JLabel();
        helpImage2.setIcon(new ImageIcon("./data/help/help6.png"));
        helpImage2.setBounds(100,0, helpViewport.width, helpImage2.getPreferredSize().height);
        tempPanel.add(helpImage2);

        // HELP 3
            // ADD FUNCTION
        String helpPanel3text = "When selecting the add function [7] the Side Panel display is updated to allow the application user to enter POI details (Name, Type, <br>" +
                "Room Number, Description). The POI location can be set by clicking the Set Position button [11] which temporarily disables side panel features to allow <br>" +
                " users to ‘Click on Map’. After a location on the visible map has been clicked with the mouse, the location coordinates are updated in the side panel <br>" +
                " text after ‘Current Pos:’ and selecting the Click on Map button [12] confirms the clicked location. This allows the user to submit [13] their POI <br>" +
                "details, which displays a confirmation dialog [14], and the user can then close [14] the add function side panel. ";
        JTextPane helpPanel3 = new JTextPane();
        helpPanel3.setContentType("text/html");
        helpPanel3.setEditorKit(new HTMLEditorKit());
        helpPanel3.setText(helpPanel1text);
        helpPanel3.setEditable(false);
        helpPanel3.setFont(new java.awt.Font("Segoe UI", 0, 12));
        helpPanel3.setBounds(0,0, helpViewport.width, 50);
        helpPanel3.setBackground(this.getBackground());
        tempPanel.add(helpPanel3);

            // SET UP HELP1 IMAGE
        JLabel helpImage3 = new JLabel();
        helpImage3.setIcon(new ImageIcon("./data/help/help7.png"));
        helpImage3.setBounds(100,0, helpViewport.width, helpImage3.getPreferredSize().height);
        tempPanel.add(helpImage3);

        //HELP 4
            // POI INTERACTION
        String helpPanel4text = "A selected POI can be distinguished by its change in color to black [16]. User-created and built-in POIs can be distinguished by the <br>" +
                "options available in their POI dialog. User-created POI dialogs [17] provide buttons to ‘Favorite’, ‘Edit’ and ‘Delete’ their POI. Alternatively, <br>" +
                "built-in POI dialogs [18] will only display a ‘Favorite’ button for users to favorite built-in POIs. Note that all POI dialogs will appear as [17] <br>" +
                " for developer accounts with all three buttons enabled. The ‘Edit’ button will update the Side Panel with similar features as when the add function <br>" +
                "is invoked allowing the user to edit and update all properties of the POI. ";
        JTextPane helpPanel4 = new JTextPane();
        helpPanel4.setContentType("text/html");
        helpPanel4.setEditorKit(new HTMLEditorKit());
        helpPanel4.setText(helpPanel4text);
        helpPanel4.setEditable(false);
        helpPanel4.setFont(new java.awt.Font("Segoe UI", 0, 12));
        helpPanel4.setBounds(0,0, helpViewport.width, 50);
        helpPanel4.setBackground(this.getBackground());
        tempPanel.add(helpPanel4);

            // SET UP HELP4 IMAGE
        JLabel helpImage4 = new JLabel();
        helpImage4.setIcon(new ImageIcon("./data/help/help4.png"));
        helpImage4.setBounds(100,0, helpViewport.width, helpImage4.getPreferredSize().height);
        tempPanel.add(helpImage4);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == back) {
            this.dispose();
            new LandingPage();

        }
    }
}
