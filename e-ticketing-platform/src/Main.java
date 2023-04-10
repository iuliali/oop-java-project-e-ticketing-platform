import enums.LocationType;
import enums.TicketCategory;
import models.Event;
import models.Location;
import models.SingleDayEvent;
import models.TicketEvent;
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
        LocationService locationService = new LocationServiceImpl();
        EventService eventService = new EventServiceImpl();
        TicketService ticketService = new TicketServiceImpl(eventService);
        UserService userService = new UserServiceImpl(ticketService);

        Location berariaH = new Location("Beraria H","Soseaua Kiseleff 32" ,800, LocationType.STAND);


        Map<TicketCategory, Integer> ticketsConcertPop = Map.of(TicketCategory.VIP, 300,
                                                                TicketCategory.GENERAL_ENTRANCE, 500);

        Event concertPop = new SingleDayEvent("Concert Pop", berariaH, ticketsConcertPop, LocalDateTime.now().plusDays(4));

        locationService.addLocation(berariaH);

        eventService.addEvent(concertPop);

        userService.registerNewUser("georgiana99", "Iulia", "Antonescu", LocalDateTime.now().minusYears(20));
        userService.registerNewUser("andrei645", "Andrei", "Andries", LocalDateTime.now().minusYears(25));

        System.out.println(userService.getUsers());

        System.out.println(locationService.getLocations());

        TicketEvent ticketEvent = userService.buyTicket(userService.getUserByUserName("georgiana99"),concertPop,TicketCategory.GENERAL_ENTRANCE);
        System.out.println(ticketEvent);
    }
}