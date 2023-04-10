package models;

import enums.LocationType;

public class Location {

    private String name;
    private String address;
    private int totalCapacity;
    private LocationType type;

    @Override
    public String toString() {
        return "\nLocation{\n" +
                "\tname= " + name +  ", \n"
                +"\taddress= " + address +  ", \n"
                +"\ttotalCapacity= " + totalCapacity + ", \n"
                +"\ttype= " + type +
                '}';
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public Location(String name, String address, int totalCapacity, LocationType type) {
        this.name = name;
        this.address = address;
        this.totalCapacity = totalCapacity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
