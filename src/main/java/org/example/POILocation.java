package org.example;

public class POILocation {
    public Building building;
    public Floor floor;
    private Poi roomName;
    public Poi poi;

    // Favourited POIs are defined by these 3
    public POILocation(Building building, Floor floor, Poi roomName) {
        this.building = building;
        this.floor = floor;
        this.roomName = roomName;
    }


    /**
     *
     * @return the building name (MC, NCB, AH)
     */
    public Building getBuilding() {
        return building;
    }

    /**
     *
     * @return the floor number
     */
    public Floor getFloor() {
        return floor;
    }

    /**
     *
     * @return the room name (e.g., MC 17)
     */
    public Poi getRoomName() {
        return roomName;
    }

    public Poi getPOI() {
        return poi;
    }
}

