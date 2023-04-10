package validators;

import enums.TicketCategory;
import models.Event;
import models.Location;
import models.MultiDayEvent;
import models.SingleDayEvent;

import java.util.List;
import java.util.Map;

public class EventValidator {
    public static boolean validateTicketsToSell(Location location, Map<TicketCategory, Integer> ticketsMap) {
        int noTickets = ticketsMap.values().stream().reduce(0, Integer::sum);
        int locationCapacity = location.getTotalCapacity();
        return noTickets <= locationCapacity;
    }
    public static boolean validateSingleDayEvent(MultiDayEvent multiDayEvent, SingleDayEvent event) {
        return multiDayEvent.getStartDate().isBefore(event.getStartDate()) &&
                multiDayEvent.getEndDate().isAfter(event.getStartDate().plusMinutes((long) event.getDuration()));
    }

    public static boolean validateSingleDayEvents(List<Event> events) {
        return events != null && events.size()!= 0 && events.stream()
                .map(x -> x instanceof SingleDayEvent)
                .reduce(true, Boolean::logicalAnd);
    }
}
