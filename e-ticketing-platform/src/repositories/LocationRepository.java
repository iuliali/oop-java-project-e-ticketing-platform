package repositories;

import models.Event;
import models.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    List<Location> locations;

    public LocationRepository() {
        this.locations = new ArrayList<>();
    }

    public List<Location> getLocations() {
        //TODO:ADD EXCEPTION
        return locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }
}
