package models;

import enums.TicketCategory;

import java.time.LocalDateTime;
import java.util.Map;

public class SingleDayEvent extends Event {
    private float durationInMin;

    public float getDuration() {
        return durationInMin;

    }

    @Override
    public String toString() {
        return "SingleDayEvent{" + "\n" +
                "\t"+super.toString() +", \n" +
                "\tduration: " + durationInMin +", \n" +
                "\tend at : " + super.getStartDate().plusMinutes((long) durationInMin)+"\n" +
                '}';
    }

    public void setDuration(float duration) {
        this.durationInMin = duration;
    }

    public SingleDayEvent(String name, Location location, Map<TicketCategory, Integer> ticketsAvailable,
                          LocalDateTime startDate) {
        super(name, location, startDate, ticketsAvailable);

    }
}
