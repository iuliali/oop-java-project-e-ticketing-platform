package services.impl;

import models.Location;
import repositories.LocationRepository;
import services.LocationService;

import java.util.List;

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl() {
        this.locationRepository = new LocationRepository();
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.getLocations();
    }

    @Override
    public void addLocation(Location location) {
        locationRepository.addLocation(location);
    }
}
