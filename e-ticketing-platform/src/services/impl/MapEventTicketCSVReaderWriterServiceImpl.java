package services.impl;

import enums.TicketCategory;
import models.MapEventTicketsConfiguration;
import services.CSVReaderWriterService;

import java.time.format.DateTimeFormatter;

import static constants.Constants.DELIMITER;
import static constants.Constants.MAP_EVENT_TICKET_FILE_NAME;

public class MapEventTicketCSVReaderWriterServiceImpl implements CSVReaderWriterService<MapEventTicketsConfiguration> {

    private static MapEventTicketCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = MAP_EVENT_TICKET_FILE_NAME;

    public static MapEventTicketCSVReaderWriterServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MapEventTicketCSVReaderWriterServiceImpl();
        }
        return INSTANCE;
    }

    private MapEventTicketCSVReaderWriterServiceImpl() {
    }

    @Override
    public String objectToCSV(MapEventTicketsConfiguration ob) {
        return ob.getId() + DELIMITER
                +ob.getCategory().getPriority() + DELIMITER
                +ob.getQuantity() + DELIMITER
                +ob.getEventId();
    }
    @Override
    public MapEventTicketsConfiguration processCSVLine(String line) {
        String[] split = line.split(DELIMITER);

        return new MapEventTicketsConfiguration(
                Integer.parseInt(split[0]),
                TicketCategory.values()[Integer.parseInt(split[1])],
                Integer.parseInt(split[2]),
                Integer.parseInt(split[3])
                );
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
