package repositories;

import dtos.LocationDto;
import exceptions.LocationNotFoundException;
import exceptions.UserIdNotFoundException;
import models.Location;
import models.User;
import services.impl.LocationCSVReaderWriterServiceImpl;

import java.util.List;
import java.util.Optional;

import static constants.Constants.LOCATION_NOT_FOUND;
import static constants.Constants.USER_ID_NOT_FOUND;

public class LocationRepository {
    private final List<Location> locations;
    private LocationCSVReaderWriterServiceImpl csvReaderWriterService = LocationCSVReaderWriterServiceImpl.getInstance();

    public LocationRepository() {
        this.locations = this.csvReaderWriterService.read();

        if (!this.locations.isEmpty()) {
            Integer maxId = this.locations.stream().map(Location::getId)
                    .reduce(Integer.MIN_VALUE, Integer::max);
            Location.setIdGenerator(maxId + 1);
        }

    }

    public List<Location> getLocations() {
        return this.csvReaderWriterService.read();
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        this.csvReaderWriterService.write(location);
    }

    public Optional<Location> getLocationById(Integer id) {
        return csvReaderWriterService.read().stream().filter(l-> l.getId() == id).findFirst();
    }

    public void editLocation(Integer id, LocationDto editedLocation) {
       Location location = getLocationById(id).orElseThrow(
               () -> new LocationNotFoundException(LOCATION_NOT_FOUND.formatted(id))
       );
        updateLocation(location, editedLocation);
        csvReaderWriterService.writeAll(this.locations);
    }

    private void updateLocation(Location location, LocationDto editedLocation) {

        if (editedLocation.getName()!= null && !editedLocation.getName().isEmpty()) {
            location.setName(editedLocation.getName());
        }
        if (editedLocation.getAddress()!= null && !editedLocation.getAddress().isEmpty()) {
            location.setAddress(editedLocation.getAddress());
        }
    }

    public void deleteLocation(Integer id) {
        /**
         * Only deletes LOCATION. In order to be okay events and tickets for events at that location should be deleted.
         */
        Location location = getLocationById(id).orElseThrow(
                () -> new LocationNotFoundException(LOCATION_NOT_FOUND.formatted(id))
        );
        locations.remove(location);
        csvReaderWriterService.writeAll(locations);

    }
}
