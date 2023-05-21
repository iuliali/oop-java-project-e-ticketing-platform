package repositories;

import config.DatabaseConnection;
import models.Location;
import services.impl.LocationCSVReaderWriterServiceImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.CSV_SOURCE;
import static constants.Constants.DB_SOURCE;

public class LocationRepository {
    private final List<Location> locations;
    private LocationCSVReaderWriterServiceImpl csvReaderWriterService = null;
    private Connection dbConnection = null;

    public LocationRepository(String source) {
        if (source == CSV_SOURCE){
            this.csvReaderWriterService = LocationCSVReaderWriterServiceImpl.getInstance();
            this.locations = this.csvReaderWriterService.read();
        } else if (source == DB_SOURCE) {
            dbConnection = DatabaseConnection.getConnection();
            this.locations = new ArrayList<>();
        } else {
            //TODO custom exc
            throw new RuntimeException("wrong source!");
        }

    }

    public List<Location> getLocations() {
        //TODO:ADD EXCEPTION
        return locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        this.csvReaderWriterService.write(location);
    }

//    public Location getLocationById(Integer id) {
//        //todo
//    }
}
