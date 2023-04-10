package services.impl;

import exceptions.FirstArgumentMustBeAMultiDayEventException;
import exceptions.InvalidEventsListException;
import exceptions.NoTicketsExceedsCapacityException;
import models.Event;
import models.MultiDayEvent;
import models.SingleDayEvent;
import repositories.EventRepository;
import services.EventService;
import validators.EventValidator;

import java.util.Collections;
import java.util.List;

import static constants.Constants.*;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;

    public EventServiceImpl() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public void addEvent(Event event) {
        try {
            if (!EventValidator.validateTicketsToSell(event.getLocation(), event.getTicketsAvailable())) {
                throw new NoTicketsExceedsCapacityException(NO_TICKETS_EXCEEDS_LOCATION_CAPACITY);
            }
            eventRepository.addEvent(event);
            System.out.println("Adding Event "+ event.getName() + " was successfully done");

        } catch (NoTicketsExceedsCapacityException exception) {
            System.out.println("Adding Event "+ event.getName() + " failed . An exception occured: ");
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public List<Event> getEvents(boolean sorted) {
        if (sorted) {
            return getEventsSorted();
        } else {
            return eventRepository.getEvents();
        }
    }

    @Override
    public void removeEvent(Event event) {
        eventRepository.removeEvent(event);
    }

    @Override
    public void addDayEventsToMultipleDayEvent(Event multiDayEvent, Event... events) {
        if(!(multiDayEvent instanceof MultiDayEvent)) {
            throw new FirstArgumentMustBeAMultiDayEventException(FIRST_ARGUMENT_MUST_BE_MULTI_DAY_EVENT);
        } else if (!EventValidator.validateSingleDayEvents(List.of(events))) {
            throw new InvalidEventsListException(INVALID_EVENT_LIST);
        } else {
            for (Event event : List.of(events)) {
                ((MultiDayEvent) multiDayEvent).addSingleDayEventToMultipleDaysEvent((SingleDayEvent) event);
            }
        }
    }

    @Override
    public List<Event> getEventsSorted() {
        var events = eventRepository.getEvents();
        Collections.sort(events);
        return events;
    }
}
