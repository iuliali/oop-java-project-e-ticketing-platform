package constants;

public class StatementsString {
    public static final String QUERY_GET_ALL_USERS = "SELECT * FROM users;";
    public static final String INSERT_USER = "INSERT INTO users (userName, birthDate, firstName,lastName)" +
            "VALUES(?,?,?,?);";
    public static final String QUERY_USER_GET_BY_ID = "SELECT * FROM users u WHERE u.id = ?";
    public static final String QUERY_USER_GET_BY_USERNAME = "SELECT * FROM users u WHERE u.userName = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users u WHERE u.id = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE users SET userName = ?, birthDate=?,firstName = ?, lastName= ?" +
            "WHERE id = ?;";

    public static final String QUERY_GET_ALL_LOCATIONS = "SELECT * FROM locations;";
    public static final String INSERT_LOCATION = "INSERT INTO locations (name, address, totalCapacity, type)" +
            "VALUES(?,?,?,?);";
    public static final String QUERY_LOCATION_GET_BY_ID = "SELECT * FROM locations l WHERE l.id = ?";

    public static final String QUERY_LOCATION_GET_BY_NAME = "SELECT * FROM locations l WHERE l.name = ?";
    public static final String DELETE_LOCATION_BY_ID = "DELETE FROM locations l WHERE l.id = ?";
    public static final String UPDATE_LOCATION_BY_ID = "UPDATE locations SET name = ?, address = ?" +
            "WHERE id = ?;";

    public static final String QUERY_GET_ALL_MAPS= "SELECT * FROM mapEventTickets;";
    public static final String QUERY_GET_ALL_MAPS_FOR_EVENT_ID= "SELECT * FROM mapEventTickets m WHERE m.eventId = ?;";

    public static final String INSERT_MAP = "INSERT INTO mapEventTickets (category, quantity, eventId)" +
            "VALUES(?,?,?);";

    public static final String INSERT_EVENT = "INSERT INTO events (name, locationId, startDate, EndDate, eventType) " +
            "VALUES(?,?,?,?,?);";

    public static final String INSERT_CONCERT = "INSERT INTO concerts (eventId, artistName) " +
            "VALUES(?,?);";
    public static final String INSERT_STANDUP = "INSERT INTO concerts (eventId, comedianName) " +
            "VALUES(?,?);";

    public static final String QUERY_EVENT_GET_BY_ID = "SELECT * FROM events e, locations l WHERE e.id = ? AND e.locationId = l.id";
    public static final String QUERY_GET_ALL_EVENTS = "SELECT * FROM events e, locations l WHERE e.locationId = l.id";

    public static final String DELETE_EVENT_BY_ID = "DELETE FROM events e WHERE e.id = ?";
    public static final String UPDATE_EVENT_BY_ID = "UPDATE events SET name = ?, startDate = ?, endDate = ?, locationId=?  " +
            "WHERE id = ?;";

    public static final String INSERT_TICKET = "INSERT INTO tickets (ticketCategory, eventId, userId) " +
            "VALUES(?,?,?);";

    public static final String QUERY_TICKET_GET_BY_ID = "SELECT * FROM tickets t, events e, users u WHERE t.id = ? " +
            "AND e.id = t.eventId " +
            "AND u.id = t.userId";
    public static final String QUERY_GET_ALL_TICKETS = "SELECT * FROM tickets t, events e, users u WHERE " +
            "e.id = t.eventId " +
            "AND u.id = t.userId";


    public static final String QUERY_TICKETS_USER_ID = "SELECT * FROM tickets t, events e, users u WHERE t.userId = ? " +
            "AND e.id = t.eventId " +
            "AND u.id = t.userId";

    public static final String QUERY_TICKETS_EVENT_ID = "SELECT * FROM tickets t, events e, users u WHERE t.eventId = ? " +
            "AND e.id = t.eventId " +
            "AND u.id = t.userId";

    public static final String DELETE_TICKET_BY_ID = "DELETE FROM tickets t WHERE t.id = ?";







}
