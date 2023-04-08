## Campus Navigation App
This application is a Geographic Information System (GIS) that uses public floor plans to make it easier for people to 
navigate the interior spaces of a university campus, specifically Western University. It aims to improve the UX of 
navigating floor plans by allowing users to search for rooms and points of interest in buildings, browse through the 
maps, hide or display types of points of interest (like classrooms or restaurants) to suit their needs and create and 
save their own personal points of interest. Additionally, the application has an editing tool to facilitate the creation 
and editing of map metadata by developers and the creation of custom points of interest by regular users.
### Required Libraries
No third party libraries are required for this program. However, it is greatly recommended to check that a recent version of the JRE and JDK are installed.
### Build Guide
In IntelliJ, use the maven sidewindow to install all dependencies and plugins. In the maven side window go to app/Plugins/assembly/assembly:single and run it to build WesternMaps.jar
### Running Guide
From group57 directory, run the following command in the terminal:
```java -jar target/WesternMaps.jar```

### User Guide
The <b>Landing Page</b> presents a drop-down list of available buildings for the user to select. When a building has been selected, click 'View Map' to confirm the selection
and proceed to the <b>Map Page</b> to view the building floor maps. The <b>About Page</b> can be accessed from this page to see the application version
and developer contact information. The 'Back' button can be used to navigate back to the <b>Login Page</b>.

The <b>Map Page</b> contains several features to navigate the given building:
To search for a specific point of interest (POI) from any floor in the building, input its full name
or its associated room number into the 'Search Bar' (a POI's id also works) and then click 'Search'.
If a POI matching your search is found, you will be directed to it and may even be taken to the floor
where the POI is. To browse POIs from the currently selected floor, use the 'Select POI' dropdown and click on a POI name to select it.
All POI layers are shown in the map by default, but they can be manually hidden/shown by the user by clicking on its associated button under 'Layers'.
The 'Favourites' dropdown stores POIs the user has favourited. These will be saved even after the application is closed and can be selected inside the dropdown.
The 'ADD' button allows admin users to create built-in POIs and regular users to create custom POIs available only to them.
To move to a new floor, press one of the floor buttons at the bottom of the page. The current floor is shown by the text to the right of the search button.
To go back to the <b>Landing Page</b>, click on the 'Back' button in the bottom right corner.
To select a POI from within the Map, click on one of the colored squares.

When the 'ADD' button is clicked, the side panel changes to allow the user to enter details for the new POI (Name, Type, Room Number, Description, and Position).
The Name, Room Number, and Description are created by inputting text into the given text boxes while the type is chosen from a dropdown menu.
To set the position of the new POI, click on the 'Set Position' button which temporarily disables all other side panel options. Then, click the spot on
the map where you want to place your POI using your mouse (this will update the Current Pos text). Once you are happy with the position you chose, click on the button now called
'Click on Map' again to confirm the position. After filling out all the information, click the Submit button to create the POI. If the POI was created, a 'Success' popup will appear. If any of the POI details has not been filled, the POI will not be created, and you will be asked to try again.
After adding all the wanted POIs, click the 'Close' button to return to the regular Map mode (this button also works to cancel making a POI).

When a POI is selected, it will display information about itself inside a popup and change its color to <b>Black</b>. Options available to the user will also appear in the popup.
When a regular user selects a custom POI, they will be allowed to edit it, add it to favourites, or delete it from the map.  If they select a built-in POI, they will only be given the option to add or remove it from their personal favorites list.
When an admin user selects a built-in POI, they will be able to edit or remove it, but they will not be able to add it to favourites (although that option will be visible in the popup).
When the 'Edit' button is pressed, the side panel will change to allow the user to input the updated POI information (works like 'ADD'). 
When the 'Delete' button is pressed, the POI is removed from the map permanently. To protect against accidental deletion, you will be asked to confirm after choosing this option.
### Account Guide / Edit Mode
To make a regular user account, simply input your chosen username and password (not hardcoded) and click the register 
button on the login page. Alternatively, you can use the default user account john_doe with the password: password123 that has predefined favourites and user-created POIs. Then click on the login button. 

Regular users can create, edit, or remove custom points of 
interest and can also favourite built-in or custom points of interest to create a favourites list.
Be aware that built-in POIs cannot be made, edited, or removed by regular users but can be manipulated by admin users 
(developers). To access developer mode, use one of the 
two admin accounts doh43 or admin both of which have the password cs2212. This will allow you to manipulate built-in 
points of interest. However, it will not allow you to create custom points of interest or favourite a point of interest.

No separate application is required to access the editing mode as it is built into the Map screen. Whether the editing 
mode is one for regular users or developers is determined by the login information provided when opening the application. 