package services.impl;

import dbconfig.DatabaseConfiguration;
import dtos.EventDto;
import exceptions.EventNotFoundException;
import exceptions.NoLocationWithRequestedIdFoundException;
import exceptions.NoTicketsExceedsCapacityException;
import models.Event;
import models.Location;
import repositories.EventRepository;
import services.EventService;
import services.LocationService;
import validators.EventValidator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;
import static constants.LogConstants.*;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final LocationService locationService;

    public EventServiceImpl(LocationService locationService, DatabaseConfiguration databaseConfiguration) {
        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));
        this.locationService = locationService;
        this.eventRepository = new EventRepository(databaseConfiguration);
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
            LOGGER.warning(ADD_EVENT_FAILED.formatted(event.getName(), exception.getMessage()));
            return;
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
    public void deleteEvent(Integer id) {
        LOGGER.info(DELETE_EVENT.formatted(id));
        try {
            Event event = getEventById(id).orElseThrow(
                    () -> new EventNotFoundException(EVENT_NOT_FOUND + id)
            );
            eventRepository.deleteEvent(id);
        } catch (RuntimeException e) {
        LOGGER.warning(DELETE_EVENT_FAILED.formatted(id, e.getMessage()));
        return;
        }
        LOGGER.info(DELETE_EVENT_SUCCESS.formatted(id));
    }

    @Override
    public void editEvent(Integer id, EventDto editedDto) {
        LOGGER.info(EDIT_EVENT.formatted(id));
        try{
            validateNewLocation(editedDto.getLocationId(), id);
            eventRepository.editEvent(id,editedDto);

        } catch (RuntimeException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public Optional<Event> getEventById(Integer id) {
        LOGGER.info(GET_EVENT_BY_ID.formatted(id));
        try {
            return eventRepository.getEventById(id);

        } catch (RuntimeException e) {
            LOGGER.warning(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Event> getUpcomingEvents() {
        return getEventsSorted().stream().filter(e -> e.getStartDate().isAfter(LocalDateTime.now())).toList();
    }

    @Override
    public Optional<Event> getEventByName(String name) {
        return eventRepository.getEventByName(name);
    }

    private void validateNewLocation(Integer locationId, Integer eventId) {
        if (locationId == null) {
            return;
        }
        Optional<Location> location =  locationService.getLocationById(locationId);

        if (location.isEmpty()) {
            throw new NoLocationWithRequestedIdFoundException(NO_LOCATION_WITH_ID_REQUESTED_FOUND, locationId);
        } else {
            Event event = eventRepository.getEventById(eventId).orElseThrow(
                    () -> new EventNotFoundException(EVENT_NOT_FOUND + eventId)
            );
            EventValidator.validateNewLocation(location.get(), event.getLocation());
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
