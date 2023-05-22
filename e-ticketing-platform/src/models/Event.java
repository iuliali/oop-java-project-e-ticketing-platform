package models;

import enums.EventType;
import enums.LocationType;

import java.time.LocalDateTime;
import java.util.List;

import static constants.Constants.TIME_FORMATTER;

public class Event implements Comparable<Event> {
    private static Integer idGenerator = 0;
    private Integer id;
    private String name;
    private Location location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EventType eventType;



    public static Integer getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(Integer idGenerator) {
        Event.idGenerator = idGenerator;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    private List<MapEventTicketsConfiguration> ticketsAvailable;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MapEventTicketsConfiguration> getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(List<MapEventTicketsConfiguration> ticketsAvailable) {
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

    public Event(String name,
                 Location location,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 EventType eventType,
                 List<MapEventTicketsConfiguration> configurations) {
        this.id = ++idGenerator;
        this.eventType = eventType;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketsAvailable = configurations;
    }

    public Event(Integer id, String name,
                 Location location,
                 LocalDateTime startDate,
                 LocalDateTime endDate,
                 EventType eventType
                 ) {
        this.id =id;
        this.eventType = eventType;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public Event(Integer id) {
        this.id =id;
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
