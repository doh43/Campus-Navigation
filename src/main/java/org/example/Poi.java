package org.example;

import org.json.JSONObject;

public class Poi {
    private String name;
    private String type;
    private int id;
    private int roomNum;
    private String desc;
    private String icon;
    private int posX;
    private int posY;
    private boolean builtIn;
    private boolean favourited;
    Poi(String name, String type, int id, int roomNum, String desc, String icon, int posX, int posY) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.roomNum = roomNum;
        this.desc = desc;
        this.icon = icon;
        this.posX = posX;
        this.posY = posY;
        this.builtIn = false;
        this.favourited = false;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

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
    // Other methods add later
}
