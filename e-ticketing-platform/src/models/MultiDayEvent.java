package models;

import enums.EventType;
import enums.TicketCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiDayEvent extends Event {
    private LocalDateTime endDate;
    private List<SingleDayEvent> singleDayEvents;

    @Override
    public String toString() {
        return "MultiDayEvent{" +
                super.toString() + "\n" +
                "endDate :" + endDate.toLocalDate() + "\n" +
                "\tsingleDayEvents :" + singleDayEvents.toString() +
                '}';
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<SingleDayEvent> getSingleDayEvents() {
        return singleDayEvents;
    }

    public void setSingleDayEvents(List<SingleDayEvent> singleDayEvents) {
        this.singleDayEvents = singleDayEvents;
    }

    public MultiDayEvent(String name, Location location, EventType eventType, int noPassesAvailable,
                         LocalDateTime startDate, LocalDateTime endDate) {
        super(name, location, startDate, eventType, Map.of(TicketCategory.PASS, noPassesAvailable));
        this.singleDayEvents = new ArrayList<>();
        this.endDate = endDate;
    }
}
