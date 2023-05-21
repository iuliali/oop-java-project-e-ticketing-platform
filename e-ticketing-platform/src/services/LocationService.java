package services;

import dtos.LocationDto;
import models.Location;

import java.util.List;

public interface LocationService {
    List<Location> getLocations() ;
    void addLocation(Location location);
    void editLocation(Integer id, LocationDto editedLocation);
    void deleteLocation(Integer id);

}
