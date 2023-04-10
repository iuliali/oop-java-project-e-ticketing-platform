package services.impl;

import models.Event;
import repositories.EventRepository;
import services.EventService;

import java.util.Collections;
import java.util.List;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;

    public EventServiceImpl() {
        this.eventRepository = new EventRepository();
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.addEvent(event);
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
    public List<Event> getEventsSorted() {
        var events = eventRepository.getEvents();
        Collections.sort(events);
        return events;
    }
}
