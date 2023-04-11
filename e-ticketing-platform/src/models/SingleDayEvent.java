package models;

import enums.EventType;
import enums.TicketCategory;

import java.time.LocalDateTime;
import java.util.Map;

import static constants.Constants.TIME_FORMATTER;

public class SingleDayEvent extends Event {
    private int durationInMin;

    public float getDuration() {
        return durationInMin;

    }

    @Override
    public String toString() {
        return "\nSingleDayEvent{"  +
                "\t"+super.toString() +
                "\tduration: " + durationInMin +", \n" +
                "\tend at : " + super.getStartDate().plusMinutes((long) durationInMin).toLocalTime().format(TIME_FORMATTER) +"\n" +
                '}';
    }

    public void setDuration(int duration) {
        this.durationInMin = duration;
    }

    public SingleDayEvent(String name, Location location, EventType eventType, Map<TicketCategory, Integer> ticketsAvailable,
                          LocalDateTime startDate, int durationInMin) {
        super(name, location, startDate, eventType, ticketsAvailable);
        this.durationInMin =durationInMin;

    }
}
