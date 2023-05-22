package constants;

import com.mysql.cj.log.Log;
import util.LoggerUtils;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;


public class Constants {
    public static final Logger LOGGER = LoggerUtils.getLogger("E-Ticketing Platform");

    public static final String NO_USER_EXCEPTION_MESSAGE = "No user register in the e-ticketing app!";
    public static final String TICKETS_ALREADY_SOLD = "Tickets for this event and category are sold out!";
    public static final String USERNAME_NOT_FOUND = "Username not found :";
    public static final String USER_ID_NOT_FOUND = "USER ID not found :";

    public static final String EVENT_DOES_NOT_HAVE_CATEGORY = "Event does not have requested category";
    public static final String NO_TICKETS_EXCEEDS_LOCATION_CAPACITY = "You cannot sell more tickets than location capacity!";
    public static final String EVENT_LIST_EMPTY = "No Event registered!";
    public static final String DAY_EVENT_NOT_IN_INTERVAL = "Day Event must happen in the context of the multi-day event!";
    public static final String FIRST_ARGUMENT_MUST_BE_MULTI_DAY_EVENT = "First Argument Must Be MultiDayEvent";
    public static final String INVALID_EVENT_LIST = "Event List is either empty or contains other type of events than SingleDayEvent!";
    public static final String USERNAME_ALREADY_EXISTS = "An user with same username is already registered!";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String CSV_FILE_NAME = "e_ticketing_platform.csv";
    public static final String LOGS_FILE_NAME = "e_ticketing_platform.log";


    public static final String DB_INFO_FILE_NAME = "db_info.csv";
    public static final String DELIMITER = ",";

    public static final String EVENT_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\event.csv";
    public static final String LOCATION_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\location.csv";
    public static final String MAP_EVENT_TICKET_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\mapeventticket.csv";

    public static final String TICKET_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\ticket.csv";
    public static final String USER_FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\csv\\user.csv";

    public static final String CSV_EXCEPTION_WRITE = "An exception occurred when writing into file : %s. Exception message: %s";
    public static final String CSV_EXCEPTION_READ = "An exception occurred when reading from file : %s. Exception message: %s";

    public static final String TICKET_NOT_FOUND = "Ticket was not found. Id searched for: %d.";
    public static final String EVENT_NOT_FOUND = "Event was not found. Id searched for: %d.";
    public static final String LOGGER_CONFIG_EXCEPTION = "Error while configuring logging : %s";



}
