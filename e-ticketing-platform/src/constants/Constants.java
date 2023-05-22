package constants;

import util.LoggerUtils;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


public class Constants {
    public static final String DB_EXCEPTION = "A database exception occurred";
    public static final String NO_USER_EXCEPTION_MESSAGE = "No user registered in the e-ticketing app!";
    public static final String TICKETS_ALREADY_SOLD = "Tickets for this event and category are sold out!";
    public static final String USERNAME_NOT_FOUND = "Username not found :";
    public static final String USER_ID_NOT_FOUND = "USER ID not found :";

    public static final String EVENT_DOES_NOT_HAVE_CATEGORY = "Event does not have requested category";
    public static final String NO_TICKETS_EXCEEDS_LOCATION_CAPACITY = "You cannot sell more tickets than location capacity!";
    public static final String EVENT_LIST_EMPTY = "No Event registered!";
    public static final String EVENT_NOT_FOUND = "Event was not found, id searched for:";

    public static final String USERNAME_ALREADY_EXISTS = "An user with same username is already registered!";
    public static final String NO_LOCATION_WITH_ID_REQUESTED_FOUND = "There is no location with the id provided: ";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String LOGS_FILE_NAME = "e_ticketing_platform.log";

    public static final String DB_INFO_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\db\\db_info.csv";
    public static final String DELIMITER = ",";
    public static final Logger LOGGER = LoggerUtils.getLogger("E-ticketing platform");

    public static final String EVENT_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\event.csv";
    public static final String LOCATION_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\location.csv";
    public static final String MAP_EVENT_TICKET_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\mapeventticket.csv";

    public static final String TICKET_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\ticket.csv";
    public static final String USER_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\user.csv";

}
