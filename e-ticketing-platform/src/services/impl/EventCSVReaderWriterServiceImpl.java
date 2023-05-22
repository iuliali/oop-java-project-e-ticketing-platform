package services.impl;

import enums.EventType;
import models.Concert;
import models.Event;
import models.Location;
import models.StandUp;
import services.CSVReaderWriterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static constants.Constants.DELIMITER;
import static constants.Constants.EVENT_FILE_NAME;

public class EventCSVReaderWriterServiceImpl implements CSVReaderWriterService<Event> {

    private static EventCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = EVENT_FILE_NAME;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static EventCSVReaderWriterServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventCSVReaderWriterServiceImpl();
        }
        return INSTANCE;
    }

    private EventCSVReaderWriterServiceImpl() {
    }

    @Override
    public String objectToCSV(Event ob) {
        String add = "";
        if(ob instanceof Concert){
            add += DELIMITER +((Concert) ob).getArtistName();
        } else if (ob instanceof StandUp) {
            add += DELIMITER + ((StandUp) ob).getComedianName();
        }
        return ob.getId()+ DELIMITER +
                ob.getName() + DELIMITER
                + ob.getLocation().getId() + DELIMITER
                + ob.getStartDate().format(formatter) + DELIMITER
                + ob.getEndDate().format(formatter) + DELIMITER
                + ob.getEventType().getValue() + add;
    }
    @Override
    public Event processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        LocalDateTime start = LocalDateTime.parse(split[3], formatter);
        LocalDateTime end = LocalDateTime.parse(split[4], formatter);
        EventType eventType = EventType.values()[Integer.parseInt(split[5])];
        if (eventType == EventType.CONCERT) {
            return new Concert(Integer.parseInt(split[0]),
                    split[1],
                    new Location(Integer.parseInt(split[2])),
                    start,
                    end,
                    EventType.values()[Integer.parseInt(split[5])],
                    split[6]);
        } else  if (eventType == EventType.STAND_UP) {
            return new StandUp(Integer.parseInt(split[0]),
                    split[1],
                    new Location(Integer.parseInt(split[2])),
                    start,
                    end,
                    EventType.values()[Integer.parseInt(split[5])],
                    split[6]);
        }
        return new Event(Integer.parseInt(split[0]),
                split[1],
                new Location(Integer.parseInt(split[2])),
                start,
                end,
                EventType.values()[Integer.parseInt(split[5])]);
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }

}
