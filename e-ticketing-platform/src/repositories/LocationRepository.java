package repositories;

import models.Event;
import models.Location;
import services.impl.LocationCSVReaderWriterServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    private List<Location> locations;
    private LocationCSVReaderWriterServiceImpl csvReaderWriterService;

    public LocationRepository() {
        this.csvReaderWriterService = LocationCSVReaderWriterServiceImpl.getInstance();
        this.locations = this.csvReaderWriterService.read();
    }

    public List<Location> getLocations() {
        //TODO:ADD EXCEPTION
        return locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        this.csvReaderWriterService.write(location);
    }
}
