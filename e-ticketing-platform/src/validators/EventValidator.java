package validators;

import exceptions.LocationUpdateException;
import models.Location;
import models.MapEventTicketsConfiguration;

import java.util.List;

import static constants.Constants.NEW_LOCATION_CANNOT_BE_ADDED;

public class EventValidator {
    public static boolean validateTicketsToSell(Location location, List<MapEventTicketsConfiguration> ticketsMap) {
        int noTickets = ticketsMap.stream().map(MapEventTicketsConfiguration::getQuantity).reduce(0, Integer::sum);
        int locationCapacity = location.getTotalCapacity();
        return noTickets <= locationCapacity;
    }

    public static void validateNewLocation(Location newLocation, Location oldLocation) {
        if (oldLocation.getTotalCapacity() > newLocation.getTotalCapacity()) {
            throw new LocationUpdateException(NEW_LOCATION_CANNOT_BE_ADDED,oldLocation.getTotalCapacity(),
                    newLocation.getTotalCapacity());
        }
    }
}
