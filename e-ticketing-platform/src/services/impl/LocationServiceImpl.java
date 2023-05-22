package services.impl;

import dbconfig.DatabaseConfiguration;
import dtos.LocationDto;
import models.Location;
import repositories.LocationRepository;
import services.LocationService;

import java.util.List;
import java.util.Optional;

import static constants.Constants.LOGGER;
import static constants.LogConstants.*;

public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;


    public LocationServiceImpl(DatabaseConfiguration databaseConfiguration) {
        this.locationRepository = new LocationRepository(databaseConfiguration);
        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));

    }

    @Override
    public List<Location> getLocations() {
        LOGGER.info(GET_LOCATIONS_CALL);

        return locationRepository.getLocations();
    }

    @Override
    public void addLocation(Location location) {
        LOGGER.info(ADD_LOCATION_CALL.formatted(location.getName()));
        locationRepository.addLocation(location);
        LOGGER.info(ADD_LOCATION_SUCCESS.formatted(location.getName(), location.getId()));

    }

    @Override
    public void editLocation(Integer id, LocationDto editedLocation) {
        LOGGER.info(EDIT_LOCATION);
        locationRepository.editLocation(id,editedLocation);
        LOGGER.info(EDIT_LOCATION_SUCCESS.formatted(editedLocation.getName(), id));

    }

    @Override
    public void deleteLocation(Integer id) {
        LOGGER.info(DELETE_LOCATION.formatted(id));
        locationRepository.deleteLocation(id);
        LOGGER.info(DELETE_LOCATION_SUCCESS.formatted(id));
    }

    @Override
    public Optional<Location> getLocationById(Integer id) {
        return locationRepository.getLocationById(id);
    }
}
