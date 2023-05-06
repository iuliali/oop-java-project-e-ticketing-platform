package services.impl;

import enums.LocationType;
import models.Location;
import services.CSVReaderWriterService;

import java.time.format.DateTimeFormatter;

public class LocationCSVReaderWriterServiceImpl implements CSVReaderWriterService<Location> {

    private static LocationCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\location.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

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
        return ob.getName() + DELIMITER
                + ob.getAddress() + DELIMITER
                + ob.getTotalCapacity() + DELIMITER
                + ob.getType();
    }
    @Override
    public Location processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        return new Location(split[0], split[1], Integer.parseInt(split[2]), LocationType.valueOf(split[3]));
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }


}
