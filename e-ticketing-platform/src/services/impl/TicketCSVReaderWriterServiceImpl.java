package services.impl;

import enums.TicketCategory;
import models.TicketEvent;
import services.CSVReaderWriterService;

import static constants.Constants.DELIMITER;
import static constants.Constants.TICKET_FILE_NAME;

public class TicketCSVReaderWriterServiceImpl implements CSVReaderWriterService<TicketEvent> {
    private static TicketCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = TICKET_FILE_NAME;

    public static TicketCSVReaderWriterServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TicketCSVReaderWriterServiceImpl();
        }
        return INSTANCE;
    }

    private TicketCSVReaderWriterServiceImpl() {
    }

    @Override
    public String objectToCSV(TicketEvent ob) {
        return ob.getId() + DELIMITER +
                ob.getEvent().getId() + DELIMITER
                + ob.getTicketCategory().getPriority();
    }
    @Override
    public TicketEvent processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        return new TicketEvent(Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        TicketCategory.values()[Integer.parseInt(split[2])]);
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }

}
