import dbconfig.DatabaseConfiguration;
import services.EventService;
import services.LocationService;
import services.TicketService;
import services.UserService;
import services.impl.EventServiceImpl;
import services.impl.LocationServiceImpl;
import services.impl.TicketServiceImpl;
import services.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Initializare DB si servicii:");

        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        LocationService locationService = new LocationServiceImpl(databaseConfiguration);
        EventService eventService = new EventServiceImpl(locationService, databaseConfiguration);
        TicketService ticketService = new TicketServiceImpl(eventService, databaseConfiguration);
        UserService userService = new UserServiceImpl(ticketService, databaseConfiguration);

        System.out.println("get in toate serviciile:");

        System.out.println(userService.getUsers());
        System.out.println(ticketService.getTickets());
        System.out.println(eventService.getEvents(true));
        System.out.println(locationService.getLocations());






    }
}