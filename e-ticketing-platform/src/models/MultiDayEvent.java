package models;

import enums.EventType;
import enums.TicketCategory;
import exceptions.DayEventNotInIntervalException;
import validators.EventValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static constants.Constants.DAY_EVENT_NOT_IN_INTERVAL;

public class MultiDayEvent extends Event {
    private LocalDateTime endDate;
    private List<SingleDayEvent> singleDayEvents;

    @Override
    public String toString() {
        return "MultiDayEvent{" +
                super.toString() + "\n" +
                "\tendDate :" + endDate.toLocalDate() + "\n" +
                "\tsingleDayEvents :" + singleDayEvents.stream().map(Event::getName) +
                '}';
    }
    public void addSingleDayEventToMultipleDaysEvent(SingleDayEvent event) {
        if (!EventValidator.validateSingleDayEvent(this, event)){
            throw new DayEventNotInIntervalException(DAY_EVENT_NOT_IN_INTERVAL);
        } else {
            this.singleDayEvents.add(event);
        }
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
