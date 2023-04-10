package models;

import enums.TicketCategory;
import exceptions.NoTicketsExceedsCapacityException;
import validators.EventValidator;

import java.time.LocalDateTime;
import java.util.Map;

import static constants.Constants.NO_TICKETS_EXCEEDS_LOCATION_CAPACITY;

public abstract class Event implements Comparable<Event> {
    private String name;
    private Location location;
    private LocalDateTime startDate;
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
                "\tlocation=" + location + ", \n"+
                "\tstart date=" + startDate.toLocalDate() + ", \n";
    }

    public Event(String name, Location location, LocalDateTime startDate, Map<TicketCategory, Integer> ticketsAvailable) {
        if (!EventValidator.validateTicketsToSell(location, ticketsAvailable)) {
            throw new NoTicketsExceedsCapacityException(NO_TICKETS_EXCEEDS_LOCATION_CAPACITY);
        }
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
