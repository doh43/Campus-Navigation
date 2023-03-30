package org.example;

public class PoiLocation {

    private Building building;
    private Floor floor;
    private Poi roomName;

    public PoiLocation(Building building, Floor floor, Poi roomName) {
        this.building = building;
        this.floor = floor;
        this.roomName = roomName;
    }

    public void removePoi() {
        // TODO: remove POI from floor
    }

    public Building getBuilding() {
        return building;
    }

    /**
     *
     * @return the floor
     */
    public Floor getFloor() {
        return floor;
    }
    
    public Poi getRoomName() {
        return roomName;
    }

}

