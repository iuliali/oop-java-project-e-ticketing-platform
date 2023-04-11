import enums.EventType;
import enums.LocationType;
import enums.TicketCategory;
import exceptions.UserNameNotFoundException;
import models.*;
import services.EventService;
import services.LocationService;
import services.TicketService;
import services.UserService;
import services.impl.EventServiceImpl;
import services.impl.LocationServiceImpl;
import services.impl.TicketServiceImpl;
import services.impl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static constants.Constants.USERNAME_NOT_FOUND;

public class Main {
    public static void main(String[] args)
    {
        LocationService locationService = new LocationServiceImpl();
        EventService eventService = new EventServiceImpl();
        TicketService ticketService = new TicketServiceImpl(eventService);
        UserService userService = new UserServiceImpl(ticketService);

        Location berariaH = new Location("Beraria H","Soseaua Kiseleff 32" ,800,
                LocationType.STAND);

        Map<TicketCategory, Integer> ticketsConcertPop = Map.of(TicketCategory.VIP, 301,
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
        Location areneleRomane = new Location("Arenele Romane","Parcul Carol", 700,
                LocationType.STAND);
        Event ouOfOfficeFest = new MultiDayEvent("outOfOfficeFest", areneleRomane, EventType.CONCERT, 700,
                LocalDateTime.of(2023,5,6, 16, 0, 0),
                LocalDateTime.of(2023,5,7, 23, 59, 59)
                );
        Event firstDayFest = new SingleDayEvent("Concert Alternosfera", areneleRomane,EventType.FESTIVAL_DAY,
                Map.of(TicketCategory.DAY_PASS, 700),
                LocalDateTime.of(2023,5,6, 18, 0, 0),
                100
                );
        Event secondDayFest = new SingleDayEvent("Concert ZOB", areneleRomane,EventType.FESTIVAL_DAY,
                Map.of(TicketCategory.DAY_PASS, 700),
                LocalDateTime.of(2023,5,7, 19, 0, 0),
                60
        );


        //1
        locationService.addLocation(berariaH);
        locationService.addLocation(salaPalatului);
        locationService.addLocation(areneleRomane);

        //2
        eventService.addEvent(concertSimfonic);
        eventService.addEvent(concertPop); /// da eroare
        eventService.addEvent(ouOfOfficeFest);
        //3
        eventService.addDayEventsToMultipleDayEvent(ouOfOfficeFest,firstDayFest,secondDayFest);
        //4
        System.out.println(eventService.getEventsSorted());
        //5
        System.out.println(locationService.getLocations());
        //6
        userService.registerNewUser("georgiana99", "Iulia",
                "Antonescu",
                LocalDateTime.of(2000,5,7, 19, 0, 0));
        //erorr for next action ->
        userService.registerNewUser("georgiana99", "Georgiana",
                "Marinescu",
                LocalDateTime.of(2005,5,7, 19, 0, 0));
        userService.registerNewUser("andrei645", "Andrei",
                "Andries",
                LocalDateTime.of(1996,7,7, 19, 0, 0));

        //7
        userService.getUsers();

        //8
        try {
            User user1= userService.getUserByUserName("georgiana99").orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, "georgiana99")
            );
            System.out.println(user1);
            User user2 = userService.getUserByUserName("georgiana89").orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, "georgiana89")
            );
            System.out.println(user2);
        } catch (UserNameNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        //9
        TicketEvent ticket = userService.buyTicket("georgiana99",
                concertSimfonic, TicketCategory.GENERAL_ENTRANCE).orElseThrow();
        System.out.println(ticket);
        TicketEvent ticket2 = userService.buyTicket("georgiana99",
                ouOfOfficeFest, TicketCategory.PASS).orElseThrow();
        System.out.println(ticket2);

        //10 print soldtickets
        ticketService.showSoldTicketsByEvents();

    }
}