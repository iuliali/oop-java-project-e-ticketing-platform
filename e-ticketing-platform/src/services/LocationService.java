package services;

import dtos.LocationDto;
import models.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> getLocations() ;
    void addLocation(Location location);
    void editLocation(Integer id, LocationDto editedLocation);
    void deleteLocation(Integer id);
    Optional<Location> getLocationById(Integer id);

}
