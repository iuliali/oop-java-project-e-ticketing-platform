package validators;

import models.Event;
import models.Location;
import models.MapEventTicketsConfiguration;

import java.util.List;

public class EventValidator {
    public static boolean validateTicketsToSell(Location location, List<MapEventTicketsConfiguration> ticketsMap) {
        int noTickets = ticketsMap.stream().map(MapEventTicketsConfiguration::getQuantity).reduce(0, Integer::sum);
        int locationCapacity = location.getTotalCapacity();
        return noTickets <= locationCapacity;
    }

}
