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
        this.setTitle("Western Campus Navigation - Help");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // PAGE TITLE
        String HelpText = "<html> <h1>Help Guide</h1>";
        JTextPane HelpTextPane = new JTextPane();
        HelpTextPane.setContentType("text/html");
        HelpTextPane.setText(HelpText);
        HelpTextPane.setEditable(false);
        HelpTextPane.setBounds(5, 5, 950, 100);
        HelpTextPane.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        HelpTextPane.setBackground(this.getBackground());
        this.add(HelpTextPane);

        // BACK BUTTON
        back = new JButton("Back");
        back.setBounds(870, 734, 100, 40);
        back.addActionListener(this);
        back.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        this.add(back);

        // SCROLL PANE WITH ALL THE HELP GUIDE CONTENT
        JScrollPane helpScroll = new JScrollPane();
        helpScroll.setBounds(0,100, 986, 620);
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
        String helpPanel1text = "<b>[1]</b> The <b>Landing Page</b> presents a drop-down list of available buildings for the user to select. <b>[2]</b> When a building has been selected, click 'View Map' to confirm the <br>selection " +
                "and proceed to the <b>Map Page</b> to view the building floor maps. The <b>About Page</b> can be accessed from this page to see the application version " +
                "and <br>developer contact information. The 'Back' button can be used to navigate back to the <b>Login Page</b>.";
        JTextPane helpPanel1 = new JTextPane();
        helpPanel1.setContentType("text/html");
        helpPanel1.setEditorKit(new HTMLEditorKit());
        helpPanel1.setText(helpPanel1text);
        helpPanel1.setEditable(false);
        helpPanel1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
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
        String helpPanel2text = "The <b>Map Page</b> contains several features to navigate the given building: <br>" +
                "<b>[3]</b> To search for a specific point of interest (POI) from any floor in the building, input its full name" +
                " or its associated room number into the 'Search Bar' <br>(a POI's id also works) and then click 'Search'." +
                " If a POI matching your search is found, you will be directed to it and may even be taken to the floor " +
                "where the POI is.<br> <b>[4]</b> To browse POIs from the currently selected floor, use the 'Select POI' dropdown and click on a POI name to select it.<br>" +
                "<b>[5]</b> All POI layers are shown in the map by default, but they can be manually hidden/shown by the user by clicking on its associated button under 'Layers'.<br>"
                + "<b>[6]</b> The 'Favourites' dropdown stores POIs the user has favourited. These will be saved even after the application is closed and can be selected inside the dropdown.<br>"
                + "<b>[7]</b> The 'ADD' button allows admin users to create built-in POIs and regular users to create custom POIs available only to them.<br>"
                + "<b>[8]</b> To move to a new floor, press one of the floor buttons at the bottom of the page. The current floor is shown by the text to the right of the search button.<br>"
                + "<b>[9]</b> To go back to the <b>Landing Page</b>, click on the 'Back' button at the bottom right corner.<br>"
                + "<b>[10]</b> To select a POI from within the Map, click on one of the colored squares.";
        JTextPane helpPanel2 = new JTextPane();
        helpPanel2.setContentType("text/html");
        helpPanel2.setEditorKit(new HTMLEditorKit());
        helpPanel2.setText(helpPanel2text);
        helpPanel2.setEditable(false);
        helpPanel2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        helpPanel2.setBounds(0,0, helpViewport.width, 50);
        helpPanel2.setBackground(this.getBackground());
        tempPanel.add(helpPanel2);

            // SET UP HELP2 IMAGE
        JLabel helpImage2 = new JLabel();
        helpImage2.setIcon(new ImageIcon("./data/help/help6.png"));
        helpImage2.setBounds(50,0, helpViewport.width, helpImage2.getPreferredSize().height);
        tempPanel.add(helpImage2);

        // HELP 3
            // ADD FUNCTION
        String helpPanel3text = "When the 'ADD' button is clicked, the side panel changes to allow the user to enter details for the new POI (Name, Type, Room Number, Description, and Position).<br>"
                + "The Name, Room Number, and Description are created by inputting text into the given text boxes while the type is chosen from a dropdown menu.<br>"
                + "<b>[11]</b> To set the position of the new POI, click on the 'Set Position' button which temporarily disables all other side panel options. Then, click the spot on "
                + "the map <br>where you want to place your POI using your mouse (this will update the Current Pos text). <b>[12]</b> Once you are happy with the position you chose, click on the <br>button now called"
                + " 'Click on Map' again to confirm the position. <b>[13]</b> After filling out all the information, click the Submit button to create the POI.<br><b>[14]</b> If the POI was created, a 'Success' popup will appear. If any of the POI details has not been filled, the POI will not be created and you will be asked to try again.<br>"
                + "<b>[15]</b> After adding all the wanted POIs, click the 'Close' button to return to the regular Map mode (this button also works to cancel making a POI).";
        JTextPane helpPanel3 = new JTextPane();
        helpPanel3.setContentType("text/html");
        helpPanel3.setEditorKit(new HTMLEditorKit());
        helpPanel3.setText(helpPanel3text);
        helpPanel3.setEditable(false);
        helpPanel3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        helpPanel3.setBounds(0,0, helpViewport.width, 50);
        helpPanel3.setBackground(this.getBackground());
        tempPanel.add(helpPanel3);

            // SET UP HELP1 IMAGE
        JLabel helpImage3 = new JLabel();
        helpImage3.setIcon(new ImageIcon("./data/help/help7.png"));
        helpImage3.setBounds(50,0, helpViewport.width, helpImage3.getPreferredSize().height);
        tempPanel.add(helpImage3);

        //HELP 4
            // POI INTERACTION
        String helpPanel4text = "<b>[16]</b> When a POI is selected, it will display information about itself inside a popup and change its color to <b>Black</b>. Options available to the user will also appear in the <br>popup." +
                " <b>[17]</b> When a regular user selects a custom POI, they will be allowed to edit it, add it to favourites, or delete it from the map. <b>[18]</b> If they select a built-in <br>POI, they will only be given the option to add or remove it from their personal favorites list." +
                " When an admin user selects a built-in POI, they will be able to edit or <br>remove it but they will not be able to add it to favourites (although that option will be visible in the popup)." +
                " When the 'Edit' button is pressed, the side panel will <br>change to allow the user to input the updated POI information (works like 'ADD')." +
                " When the 'Delete' button is pressed, the POI is removed from the map permanently.<br> To protect against accidental deletion, you will be asked to confirm after choosing this option.";
        JTextPane helpPanel4 = new JTextPane();
        helpPanel4.setContentType("text/html");
        helpPanel4.setEditorKit(new HTMLEditorKit());
        helpPanel4.setText(helpPanel4text);
        helpPanel4.setEditable(false);
        helpPanel4.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        helpPanel4.setBounds(0,0, helpViewport.width, 50);
        helpPanel4.setBackground(this.getBackground());
        tempPanel.add(helpPanel4);

            // SET UP HELP4 IMAGE
        JLabel helpImage4 = new JLabel();
        helpImage4.setIcon(new ImageIcon("./data/help/help4.png"));
        helpImage4.setBounds(120,0, helpViewport.width, helpImage4.getPreferredSize().height);
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
