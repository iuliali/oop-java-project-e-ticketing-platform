package services.impl;

import models.Location;
import repositories.LocationRepository;
import services.LocationService;

import java.util.List;

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(String source) {
        this.locationRepository = new LocationRepository(source="csv");
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
