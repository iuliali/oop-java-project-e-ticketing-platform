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

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final LocationService locationService;

    public EventServiceImpl(LocationService locationService) {
        LOGGER.info("Event Service Created");
        this.locationService = locationService;
        this.eventRepository = new EventRepository(locationService.getLocations());
    }

    @Override
    public void addEvent(Event event) {
        try {
            if (!EventValidator.validateTicketsToSell(event.getLocation(), event.getTicketsAvailable())) {
                throw new NoTicketsExceedsCapacityException(NO_TICKETS_EXCEEDS_LOCATION_CAPACITY);
            }
            eventRepository.addEvent(event);

        } catch (NoTicketsExceedsCapacityException exception) {
            LOGGER.warning("Adding Event "+ event.getName() + " failed . An exception occured: "
                    + exception.getMessage());
        }
        LOGGER.info("Adding Event "+ event.getName() + " was successfully done");
    }

    @Override
    public List<Event> getEvents(boolean sorted) {
        LOGGER.info("Event Service getEvents called.");
        if (sorted) {
            return getEventsSorted();
        } else {
            return eventRepository.getEvents();
        }
    }



    @Override
    public List<Event> getEventsSorted() {
        LOGGER.info("Event Service getEvents called.");

        var events = eventRepository.getEvents();
        Collections.sort(events);
        return events;
    }
}
