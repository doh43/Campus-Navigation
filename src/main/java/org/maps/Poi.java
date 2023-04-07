/** The Poi (Point of Interest) class is used to represent a location on a map.
 * It contains information about the location's name, type, id, room number, description, icon, position (x, y),
 * whether it's built-in or not and whether it's favourited or not.
 * @author Ethan Wakefield
 * @version 1.0 */

package org.maps;

import org.json.JSONObject;

public class Poi {
    /** Name of the poi. */
    private String name;

    /** Type of the poi. */
    private String type;

    /** ID of the poi. */
    private int id;

    /** Room number of the poi. */
    private int roomNum;

    /** Description of the poi. */
    private String desc;

    /** Icon of the poi. */
    private String icon;

    /** Position of the poi on the X axis. */
    private int posX;

    /** Position of the poi on the Y axis. */
    private int posY;

    /** Indicates whether the poi is built-in or not. */
    private boolean builtIn;

    /** Indicates whether the poi is favourited or not. */
    private boolean favourited;

    /** Constructs a new Poi object.
     * @param name the name of the poi
     * @param type the type of the poi
     * @param id the ID of the poi
     * @param roomNum the room number of the poi
     * @param desc the description of the poi
     * @param icon the icon of the poi
     * @param posX the position of the poi on the X axis
     * @param posY the position of the poi on the Y axis */
    Poi(String name, String type, int id, int roomNum, String desc, String icon, int posX, int posY) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.roomNum = roomNum;
        this.desc = desc;
        this.icon = icon;
        this.posX = posX;
        this.posY = posY;
        if (SessionManager.getCurrentUser().userType().equals("admin")) {
            this.builtIn = true;
        } else {
            this.builtIn = false;
        }
        this.favourited = false;
    }

    /** Converts a JSON poi to a Poi object.
     * @param poi the JSON poi data */
    Poi(JSONObject poi) {
        this.name = poi.getString("name");
        this.type = poi.getString("type");
        this.id = poi.getInt("id");
        this.roomNum = poi.getInt("roomNum");
        this.desc = poi.getString("desc");
        this.icon = poi.getString("icon");
        this.posX = poi.getInt("posX");
        this.posY = poi.getInt("posY");
        this.builtIn = poi.getBoolean("builtIn");
        this.favourited = false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getRoomNum() { return this.roomNum; }

//    public int get
    public String getDesc() {
        return this.desc;
    }

    public int getPosX() { return this.posX; }

    public int getPosY() { return this.posY; }

    public int getId() { return this.id;}

    public boolean getBuiltIn() { return this.builtIn; }

    public boolean getFavourited() { return this.favourited; }

    /** Converts the Poi object to a JSONObject.
     * @return a JSONObject representing the Poi object */
    public JSONObject convertJSON() {
        JSONObject o = new JSONObject();
        o.put("name", this.name);
        o.put("type", this.type);
        o.put("id", this.id);
        o.put("roomNum", this.roomNum);
        o.put("desc", this.desc);
        o.put("icon", this.icon);
        o.put("posX", this.posX);
        o.put("posY", this.posY);
        o.put("builtIn", this.builtIn);
        o.put("favourited", this.favourited);
        return o;
    }
}
