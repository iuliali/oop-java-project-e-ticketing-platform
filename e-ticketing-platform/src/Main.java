import dbconfig.DatabaseConfiguration;
import dtos.EventDto;
import dtos.LocationDto;
import dtos.UserDto;
import enums.EventType;
import enums.LocationType;
import enums.TicketCategory;
import models.Event;
import models.Location;
import models.Play;
import models.StandUp;
import services.EventService;
import services.LocationService;
import services.TicketService;
import services.UserService;
import services.impl.EventServiceImpl;
import services.impl.LocationServiceImpl;
import services.impl.TicketServiceImpl;
import services.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Map;

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



//
//        eventService.editEvent(11, new EventDto("Primavara incepe cu noi",
//                null, null, 29)); // should throw an error
//        locationService.editLocation(1,
//                new LocationDto("Hard Rock Cafee", "Kiseleff 4"));
//        userService.editUser(3, new UserDto(null,null, null, "Marinescu"));
////        System.out.println(eventService.getEventsSorted());
//        Location location = locationService.getLocationById(30).get();
//
//        eventService.addEvent( new StandUp(
//                "Povesti ", "Toma",  location,
//                LocalDateTime.now().plusDays(20).plusHours(2),
//                LocalDateTime.now().plusDays(20).plusHours(6),
//                EventType.STAND_UP, Map.of(TicketCategory.GENERAL_ENTRANCE, 100)
//        )); // should throw an error and not add the event
//
//        System.out.println(eventService.getEventsSorted());
//
//        Event event = eventService.getEventById(11).get();
//
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
//        System.out.println(ticketService.searchAvailableTicketsPerEvent("primavara"));
//        locationService.addLocation(new Location("TNB","Regina Elisabeta", 50, LocationType.SEAT));
//        Location location = locationService.getLocationById(31).get();

//        eventService.addEvent(new Play(new Event("Orchestra Titanic", location,
//                LocalDateTime.now().plusDays(30),
//                LocalDateTime.now().plusDays(30).plusHours(2),
//                EventType.PLAY,
//                Map.of(TicketCategory.GENERAL_ENTRANCE, 45, TicketCategory.VIP,5)), "Andrei Andreescu"));

//        eventService.deleteEvent(14);










    }
}