package models;

import enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

public class Concert extends Event{
    private String artistName;
    public Concert(String name,
                   String artistName,
                   Location location,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   EventType eventType,
                   List<MapEventTicketsConfiguration> configurations) {
        super(name, location, startDate, endDate, eventType, configurations);
        this.artistName = artistName;
    }
    public Concert( Event event, String artistName) {
        super(event);
        this.artistName = artistName;
    }
    public Concert(Integer id,
                   String name,
                   Location location,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   EventType eventType,
                   String artistName) {
        super(id, name, location, startDate, endDate, eventType);
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Concert{" +
                super.toString() +
                "artistName='" + artistName + '\'' +
                '}';
    }
}
