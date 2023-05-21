package services.impl;

import enums.LocationType;
import models.Location;
import services.CSVReaderWriterService;

import static constants.Constants.DELIMITER;
import static constants.Constants.LOCATION_FILE_NAME;

public class LocationCSVReaderWriterServiceImpl implements CSVReaderWriterService<Location> {

    private static LocationCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = LOCATION_FILE_NAME;

    public static LocationCSVReaderWriterServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocationCSVReaderWriterServiceImpl();
        }
        return INSTANCE;
    }

    private LocationCSVReaderWriterServiceImpl() {
    }

    @Override
    public String objectToCSV(Location ob) {
        return ob.getId()+ DELIMITER +
                ob.getName() + DELIMITER
                + ob.getAddress() + DELIMITER
                + ob.getTotalCapacity() + DELIMITER
                + ob.getType();
    }
    @Override
    public Location processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        return new Location(Integer.parseInt(split[0]),
                split[1], split[2], Integer.parseInt(split[3]),
                LocationType.valueOf(split[4]));
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }


}
