import dbconfig.DatabaseConfiguration;
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

        Location berarie = new Location("Beraria H", "Kiseleff", 800, LocationType.STAND);
        locationService.addLocation(berarie);
        List<MapEventTicketsConfiguration> configs = new ArrayList<>();
        configs.add(new MapEventTicketsConfiguration(TicketCategory.GENERAL_ENTRANCE, 600));
        configs.add(new MapEventTicketsConfiguration(TicketCategory.VIP, 200));

        eventService.addEvent(new Event("Muzica si voi Buna", berarie, LocalDateTime.of(2024, 4, 16, 0, 0, 0),
                LocalDateTime.of(2024, 4, 16, 0, 0,0),
                EventType.CONCERT, configs
                ));


    }
}