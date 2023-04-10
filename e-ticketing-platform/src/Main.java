import enums.EventType;
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

        Location berariaH = new Location("Beraria H","Soseaua Kiseleff 32" ,800,
                LocationType.STAND);

        Map<TicketCategory, Integer> ticketsConcertPop = Map.of(TicketCategory.VIP, 300,
                                                                TicketCategory.GENERAL_ENTRANCE, 500);

        Event concertPop = new SingleDayEvent("Concert Pop", berariaH, EventType.CONCERT,
                            ticketsConcertPop,
                            LocalDateTime.of(2023,7,1, 18, 0, 0),
                            150);

        Location salaPalatului = new Location("Sala Palatului", "Str. Ion Campineanu 28",
                400, LocationType.SEAT);
        Event concertSimfonic = new SingleDayEvent("Concert Simfonic", salaPalatului, EventType.CONCERT,
                Map.of(TicketCategory.GENERAL_ENTRANCE, 400),
                LocalDateTime.of(2023,7,1, 18, 0, 0), 200);

        locationService.addLocation(berariaH);
        locationService.addLocation(salaPalatului);

        eventService.addEvent(concertSimfonic);
        eventService.addEvent(concertPop);
        System.out.println(eventService.getEventsSorted());


        userService.registerNewUser("georgiana99", "Iulia",
                "Antonescu",
                LocalDateTime.now().minusYears(20));
        userService.registerNewUser("andrei645", "Andrei",
                "Andries",
                LocalDateTime.now().minusYears(25));

        TicketEvent ticket = userService.buyTicket(userService.getUserByUserName("georgiana99"),
                concertSimfonic, TicketCategory.GENERAL_ENTRANCE);
        System.out.println(ticket);

    }
}