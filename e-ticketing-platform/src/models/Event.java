package models;

import enums.EventType;
import enums.TicketCategory;

import java.time.LocalDateTime;
import java.util.Map;

import static constants.Constants.TIME_FORMATTER;

public abstract class Event implements Comparable<Event> {
    private String name;
    private Location location;
    private LocalDateTime startDate;
    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    private Map<TicketCategory, Integer> ticketsAvailable;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Map<TicketCategory, Integer> getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Map<TicketCategory, Integer> ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "\n" +
                "\tname=" + name + ", \n"+
                "\tlocation=" + location.getName() + ", \n"+
                "\tstart date=" + startDate.toLocalDate() + " hours: " + startDate.toLocalTime().format(TIME_FORMATTER) + ", \n";
    }

    protected Event(String name, Location location, LocalDateTime startDate,
                 EventType eventType, Map<TicketCategory, Integer> ticketsAvailable) {
        this.eventType = eventType;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int compareTo(Event o) {

        if (!this.startDate.isEqual(o.startDate)) {
            return this.startDate.compareTo(o.startDate);
        } else {
            return this.name.compareTo(o.name);
        }
    }
}
