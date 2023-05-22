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

//        userService.registerNewUser("andreea", "Andreea", "Marinescu",
//                LocalDateTime.now().minusYears(23));

//        Location areneleRomane = new Location("Arenele Romane","Parcul Carol", 700,
//                LocationType.STAND);
//
//        Location clubBlack = new Location("Club Black","Calea Victoriei", 30,
//                LocationType.SEAT);
//
//        locationService.addLocation(areneleRomane);
//        locationService.addLocation(clubBlack);

//        Location location = locationService.getLocationById(30).get();


//        eventService.addEvent( new StandUp (
//                "Voi ce mai faceti ?", "Micutzu",  location,
//                LocalDateTime.now().plusDays(20).plusHours(2),
//                LocalDateTime.now().plusDays(20).plusHours(6),
//                EventType.STAND_UP, Map.of(TicketCategory.GENERAL_ENTRANCE, 30)
//        ));

//        System.out.println(eventService.getEventsSorted());


//        eventService.addEvent( new StandUp (
//                "Povesti ", "Toma",  location,
//                LocalDateTime.now().plusDays(20).plusHours(2),
//                LocalDateTime.now().plusDays(20).plusHours(6),
//                EventType.STAND_UP, Map.of(TicketCategory.GENERAL_ENTRANCE, 100)
//        )); // should throw an error and not add the event
//
//        System.out.println(eventService.getEventsSorted());

//        Event event = eventService.getEventById(11).get();

//        userService.registerNewUser("alina", "Alina", "Georgescu",
//                LocalDateTime.now().minusYears(20).minusMonths(5));
//        System.out.println(userService.getUsers());
//
//
//        userService.buyTicket("andreea", event, TicketCategory.GENERAL_ENTRANCE);
//
//        System.out.println(ticketService.getTickets());
//
//        userService.returnTicket("andreea", 3);










    }
}