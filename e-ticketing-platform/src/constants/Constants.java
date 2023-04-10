package constants;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String NO_USER_EXCEPTION_MESSAGE = "No user register in the e-ticketing app!";
    public static final String TICKETS_ALREADY_SOLD = "Tickets for this event and category are sold out!";
    public static final String USERNAME_NOT_FOUND = "Username not found!";
    public static final String EVENT_DOES_NOT_HAVE_CATEGORY = "Event does not have requested category";
    public static final String NO_TICKETS_EXCEEDS_LOCATION_CAPACITY = "You cannot sell more tickets than location capacity!";
    public static final String EVENT_LIST_EMPTY = "No Event registered!";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
            ;

}
