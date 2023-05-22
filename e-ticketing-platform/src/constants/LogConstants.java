package constants;


public class LogConstants {
    public static final String SERVICE_CREATED = "%s created.";
    public static final String GET_EVENT_BY_ID = "Event Service getEventById(%d) called.";
    public static final String GET_LOCATIONS_CALL = "Location Service method getLocations() called.";
    public static final String ADD_LOCATION_CALL = "Location Service method addLocation() called. New Location: %s .";
    public static final String  ADD_LOCATION_SUCCESS = "Location : %s with id : %d added successfully";
public static final String EDIT_EVENT = "Event Service editEvent(%d) called.";
    public static final String EDIT_LOCATION = "Location Service method editLocation() called with parameters id = %d, editedLocation = %s";
    public static final String EDIT_LOCATION_SUCCESS = "Location : %s with id : %d edited successfully";
    public static final String DELETE_LOCATION = "Location Service method deleteLocation() called with parameter id = %s .";
    public static final String DELETE_LOCATION_SUCCESS = "Location with id : %d deleted successfully";
    public static final String DELETE_EVENT= "Event Service deleteEvent(%d) called.";
    public static final String DELETE_EVENT_SUCCESS = "Deleting Event with id=%d  was successfully done";
    public static final String DELETE_EVENT_FAILED = "Deleting Event with id=%d  failed due to: %s. ";

    public static final String GET_EVENTS = "Event Service getEvents() called with parameter sorted = %s";
    public static final String GET_EVENTS_SORTED = "Event Service getEventsSorted() called.";
    public static final String ADD_TICKET= "TicketService method addTicket called.";
    public static final String ADD_EVENT =  "EventService method addEvent called.";
    public static final String ADD_EVENT_FAILED = "Adding Event %s failed . An exception occured: %s ";
    public static final String ADD_EVENT_SUCCESS = "Event %s id :%d was successfully added.";

    public static final String REGISTER_NEW_USER_LOG = "UserService method registerUser with parameters " +
            "userName= %s , firstName=%s , lastName =%s, birthDate= %s";
    public static final String REGISTER_USER_EXCEPTION = "User username=%s could not be registered. An exception occurred: %s ";
    public static final String REGISTER_USER_SUCCESSFUL = "User %s id :%d was successfully registered.";
    public static final String GET_USERS = "User Service method getUsers() called";
    public static final String GET_USER_BY_USERNAME = "User Service method getUserByUserName(\"%s\") called";
    public static final String  EDIT_USER_CALL = "User Service method editUser(%d) called";
    public static final String EDIT_USER_SUCCESS= "Successfully edited user with id : %d";

    public static final  String DELETE_USER_CALL = "User Service method deleteUser(%d) called" ;
    public static final String DELETE_USER_SUCCESS = "Successfully deleted user with id : %d";
    public static final String BUY_TICKET_CALL = "UserService, method buyTicket with parameters :" +
            " userName = %s, event = %s, ticketCategory = %s was called .";
    public static final String BUY_TICKET_FAILED = "Buying ticket failed due to: %s ";

    public static final String BUY_TICKET_SUCCESS = "Ticket for event %s  was successfully bought.";

    public static final String RETURN_TICKET_FAILED = "Returning ticket failed due to: %s ";

    public static final String RETURN_TICKET_SUCCESS = "Ticket with id : %s was successfully returned.";
    public static final String RETURN_TICKET_CALL = "UserService, method buyTicket with parameters :" +
            " userName = %s, ticketId = %s was called .";
    public static final String TICKET_GET_AVAILABLE_TICKET_CALL = "Ticket Service method getAvailableTicket() called with parameters: event = %s, category = %s";

    public static final String TICKET_DELETE_TICKET_CALL = "Ticket Service method deleteTicket with parameter id = %d was called";
    public static final String DELETE_TICKET_FAILED = "Exception occurred in method deleteTicket with parameter id = %d . Exception:%s ";



}

