package services.impl;

import dbconfig.DatabaseConfiguration;
import dtos.LocationDto;
import models.Location;
import repositories.LocationRepository;
import services.LocationService;

import java.util.List;
import java.util.Optional;

import static constants.Constants.LOGGER;

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;


    public LocationServiceImpl(DatabaseConfiguration databaseConfiguration) {
        this.locationRepository = new LocationRepository(databaseConfiguration);
        LOGGER.info("Location Service created.");

    }

    @Override
    public List<Location> getLocations() {
        LOGGER.info("Location Service method getLocations() called.");

        return locationRepository.getLocations();
    }

    @Override
    public void addLocation(Location location) {
        LOGGER.info("Location Service method addLocation() called.");
        locationRepository.addLocation(location);
        LOGGER.info("Location : %s with id : %d added successfully".formatted(location.getName(), location.getId()));

    }

    @Override
    public void editLocation(Integer id, LocationDto editedLocation) {
        LOGGER.info("Location Service method editLocation() called.");
        locationRepository.editLocation(id,editedLocation);
        LOGGER.info("Location : %s with id : %d edited successfully".formatted(editedLocation.getName(), id));

    }

    @Override
    public void deleteLocation(Integer id) {
        LOGGER.info("Location Service method editLocation() called.");
        locationRepository.deleteLocation(id);
        LOGGER.info("Location with id : %d deleted successfully".formatted(id));
    }

    @Override
    public Optional<Location> getLocationById(Integer id) {
        return locationRepository.getLocationById(id);
    }
}
