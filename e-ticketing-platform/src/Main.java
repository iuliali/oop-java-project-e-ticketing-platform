import dtos.LocationDto;
import dtos.UserDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static constants.Constants.USERNAME_NOT_FOUND;

public class Main {
    public static void main(String[] args)
    {
        LocationService locationService = new LocationServiceImpl();
        EventService eventService = new EventServiceImpl(locationService);
        TicketService ticketService = new TicketServiceImpl(eventService);
        UserService userService = new UserServiceImpl(ticketService);

        Location areneleRomane = new Location("Arenele Romane","Parcul Carol", 700,
                LocationType.STAND);
        locationService.addLocation(areneleRomane);
        List<MapEventTicketsConfiguration> map = new ArrayList<>();
        map.add(new MapEventTicketsConfiguration(TicketCategory.GENERAL_ENTRANCE, 500));
        map.add(new MapEventTicketsConfiguration(TicketCategory.VIP, 200));



        Event primavara = new Concert("Primavara","Delia", areneleRomane,
                LocalDateTime.of(2023,5,23, 16, 0, 0),
                LocalDateTime.of(2023,5,23, 23, 59, 59),
                EventType.CONCERT,
                map
                );
        eventService.addEvent(primavara);
        userService.registerNewUser("georgiana199", "Iulia",
                "Antonescu",
                LocalDateTime.of(2000,5,7, 19, 0, 0));

        Optional<TicketEvent> ticket = userService.buyTicket("georgiana199", primavara, TicketCategory.GENERAL_ENTRANCE);
        System.out.println(ticket);
        System.out.println(ticketService.getTickets());
        userService.returnTicket("georgiana199", 1);
        System.out.println(ticketService.getTickets());

    }
}