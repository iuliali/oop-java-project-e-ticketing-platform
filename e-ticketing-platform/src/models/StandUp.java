package models;

import enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

public class StandUp extends Event {
    String comedianName;

    public StandUp(String name,
                   String comedianName,
                   Location location,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   EventType eventType,
                   List<MapEventTicketsConfiguration> configurations) {
        super(name, location, startDate, endDate, eventType, configurations);
        this.comedianName = comedianName;
    }

    public StandUp(Integer id, String name, Location location,
                   LocalDateTime startDate, LocalDateTime endDate, EventType eventType, String comedianName) {
        super(id, name, location, startDate, endDate, eventType);
        this.comedianName = comedianName;
    }

    public String getComedianName() {
        return comedianName;
    }

    public void setComedianName(String comedianName) {
        this.comedianName = comedianName;
    }

    @Override
    public String toString() {
        return "StandUp{" +
                super.toString() +
                "comedianName='" + comedianName + '\'' +
                '}';
    }
}
