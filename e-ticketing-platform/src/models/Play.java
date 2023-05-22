package models;

import enums.EventType;
import enums.TicketCategory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Play extends Event {
    String specialGuest;

    @Override
    public String toString() {
        return "Play{" +
                super.toString()
                +
                "\tspecialGuest='" + specialGuest + '\'' +
                '}';
    }

    public Play(Event event, String specialGuest) {
        super(event);
        this.specialGuest = specialGuest;
    }

    public String getSpecialGuest() {
        return specialGuest;
    }

    public void setSpecialGuest(String specialGuest) {
        this.specialGuest = specialGuest;
    }

    public Play(String name,
                Location location,
                LocalDateTime startDate,
                LocalDateTime endDate, EventType eventType,
                List<MapEventTicketsConfiguration> configurations) {
        super(name, location, startDate, endDate, eventType, configurations);
    }

    public Play(String name, Location location, LocalDateTime startDate,
                LocalDateTime endDate, EventType eventType, Map<TicketCategory, Integer> configurations) {
        super(name, location, startDate, endDate, eventType, configurations);
    }

    public Play(Integer id, String name, Location location, LocalDateTime startDate,
                LocalDateTime endDate, EventType eventType) {
        super(id, name, location, startDate, endDate, eventType);
    }

    public Play(Event other) {
        super(other);
    }

    public Play(Integer id) {
        super(id);
    }
}
