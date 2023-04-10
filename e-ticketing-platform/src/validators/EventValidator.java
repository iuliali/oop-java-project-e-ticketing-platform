package validators;

import enums.TicketCategory;
import models.Location;

import java.util.Map;

public class EventValidator {
    public static boolean validateTicketsToSell(Location location, Map<TicketCategory, Integer> ticketsMap) {
        int noTickets = ticketsMap.values().stream().reduce(0, Integer::sum);
        int locationCapacity = location.getTotalCapacity();
        return noTickets <= locationCapacity;
    }
}
