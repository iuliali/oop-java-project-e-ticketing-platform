package services.impl;

import exceptions.NoTicketsExceedsCapacityException;
import models.Event;
import repositories.EventRepository;
import services.EventService;
import services.LocationService;
import validators.EventValidator;

import java.util.Collections;
import java.util.List;

import static constants.Constants.LOGGER;
import static constants.Constants.NO_TICKETS_EXCEEDS_LOCATION_CAPACITY;
import static constants.LogConstants.*;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final LocationService locationService;

    public EventServiceImpl(LocationService locationService) {

        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));

        this.locationService = locationService;
        this.eventRepository = new EventRepository(locationService.getLocations());
    }

    @Override
    public void addEvent(Event event) {
        LOGGER.info(ADD_EVENT);
        try {
            if (!EventValidator.validateTicketsToSell(event.getLocation(), event.getTicketsAvailable())) {
                throw new NoTicketsExceedsCapacityException(NO_TICKETS_EXCEEDS_LOCATION_CAPACITY);
            }
            eventRepository.addEvent(event);

        } catch (NoTicketsExceedsCapacityException exception) {
            LOGGER.warning(ADD_EVENT_FAILED
                    .formatted(event.getName(), exception.getMessage()));
        }
        LOGGER.info(ADD_EVENT_SUCCESS.formatted(event.getName(), event.getId()));
    }

    @Override
    public List<Event> getEvents(boolean sorted) {
        LOGGER.info(GET_EVENTS.formatted(String.valueOf(sorted)));
        if (sorted) {
            return getEventsSorted();
        } else {
            return eventRepository.getEvents();
        }
    }



    @Override
    public List<Event> getEventsSorted() {
        LOGGER.info(GET_EVENTS_SORTED);

        var events = eventRepository.getEvents();
        Collections.sort(events);
        return events;
    }
}
