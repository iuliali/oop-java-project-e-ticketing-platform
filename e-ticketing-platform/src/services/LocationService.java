package services;

import models.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getLocations() ;
    public void addLocation(Location location);
}
