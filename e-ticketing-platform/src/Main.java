import dbconfig.DatabaseConfiguration;
import dtos.EventDto;
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
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
        LocationService locationService = new LocationServiceImpl(databaseConfiguration);
        EventService eventService = new EventServiceImpl(locationService, databaseConfiguration);
        TicketService ticketService = new TicketServiceImpl(eventService, databaseConfiguration);
        UserService userService = new UserServiceImpl(ticketService, databaseConfiguration);

        System.out.println(userService.getUsers());
        System.out.println(ticketService.getTickets());
        System.out.println(eventService.getEvents(true));
        System.out.println(locationService.getLocations());






    }
}