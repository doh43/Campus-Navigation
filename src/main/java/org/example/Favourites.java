package org.example;

public class Favourites {
    private Building building;
    private Floor floor;
    private Poi roomName;

    // Favourited POIs are defined by these 3
    public Favourites(Building building, Floor floor, Poi roomName) {
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
}

