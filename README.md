## Campus Navigation App
A desktop application that uses public floor plans to allow users to navigate the interior spaces of **Western University**. Users can search for specific rooms, favourite built-in points of interest (POIs), as well as create their own custom POIs for future reference! The application also has a developer tool to allow further customization of built-in POIs to be used by regular users. 

Buildings currently supported are Middlesex College (MC), North Campus Building (NCB), and Alumni Hall (AH). 

This program is written in Java, with the GUI built using Swing. Javadocs and JUnit tests are included.
<img width="1875" alt="Screenshot 2024-02-23 at 11 38 16 PM" src="https://github.com/doh43/Campus-Navigation/assets/64013855/e833a5b9-f56d-4951-a3f9-49b1e0ce22d6">

### Features
#### User Registration and Login 
Supports new user creation and login with persistent data storage. 

#### Browsing Maps
Users can navigate through a directory of different buildings on campus. Within each building, corresponding maps are provided for each floor.

#### Searching Maps
A search menu is available for users to search for a specific POI (both user-made and built-in) on the map.

#### Built-In Points of Interest
Each floor map within a building contains built-in points of interest that users can layer onto the map while browsing or searching throughout the floor. Facilities including but not limited to classrooms, washrooms, computer labs, eateries, and even common stairways or elevators may be layered or hidden from the map.

#### Favourites
Users can favourite and un-favourite specific points of interest for quick access. Favourited POIs can be found in the favourites menu.

#### User-Created Points of Interest
In addition to built-in POIs, users may create their own custom POIs as well. Simply drag and drop a button representing a new POI, enter the corresponding information, and have your custom POI readily visible on the map.

#### Editing Tool for Developers
To access developer mode, use one of the two admin accounts: doh43 or admin, with the password: cs2212. This will allow you to manipulate built-in points of interest. However, it will not allow you to create custom points of interest or favourite points of interest. 
Regular users will not have access to admin-specific features but are still able to enjoy the full functionality of the application.

## Install and Set Up
To run Campus Navigation, first download the latest Zip file. Open the file (preferably using IntelliJ or Eclipse) and ensure all Maven dependencies are installed and/or resolved. Finally, navigate to src/main/java/org.maps and run the LoginFrame class.

### Account Guide / Edit Mode
To create a regular user account, simply input your chosen username and password (not hardcoded) and click the register 
button on the login page. Alternatively, you can use the default user account john_doe with the password: password123 that has predefined favourites and user-created POIs. Then click on the login button. 

Regular users can create, edit, or remove custom points of 
interest and can also favourite built-in or custom points of interest to create a favourites list.
Be aware that built-in POIs cannot be made, edited, or removed by regular users but can be manipulated by admin users 
(developers). 
#### Developer Mode
To access developer mode, use one of the two admin accounts: <b>doh43</b> or <b>admin</b>, both of which have the password: <b>cs2212</b>. This will allow you to manipulate built-in 
points of interest. However, it will not allow you to create custom points of interest or favourite a points of interest.

No separate application is required to access the editing mode as it is built into the Map screen. Whether the editing 
mode is one for regular users or developers and is determined by the login information provided when opening the application. 
