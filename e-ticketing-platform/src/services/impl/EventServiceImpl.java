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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;

public class EventServiceImpl implements EventService {
    public final EventRepository eventRepository;
    public final LocationService locationService;

    public EventServiceImpl(LocationService locationService, DatabaseConfiguration databaseConfiguration) {
        LOGGER.info("Event Service Created");
        this.locationService = locationService;
        this.eventRepository = new EventRepository(databaseConfiguration);
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
    public void deleteEvent(Integer id) {
        LOGGER.info("Event Service deleteEvent(%d) called.".formatted(id));
        try {
            eventRepository.deleteEvent(id);
        } catch (RuntimeException e) {
        LOGGER.warning("Deleting Event with id=" + id + " failed due to an exception :  " + e.getMessage());
        return;
        }
        LOGGER.info("Deleting Event with id="+ id + " was successfully done");
    }

    @Override
    public void editEvent(Integer id, EventDto editedDto) {
        LOGGER.info("Event Service editEvent(%d) called.".formatted(id));
        try{
            validateNewLocation(editedDto.getLocationId());
            eventRepository.editEvent(id,editedDto);

        } catch (RuntimeException e) {
            LOGGER.warning("Editing Event with" + id + " failed due to an exception :  " + e.getMessage());
            return;
        }
        LOGGER.info("Editing Event with id ="+ id + " was successfully done");
    }

    @Override
    public Optional<Event> getEventById(Integer id) {
        LOGGER.info("Event Service getEventById(%d) called.".formatted(id));
        try {
            return eventRepository.getEventById(id);

        } catch (RuntimeException e) {
            LOGGER.warning("Getting event by id failed" + e.getMessage());
        }
        return Optional.empty();
    }

    private void validateNewLocation(Integer locationId) {
        if (locationId == null) {
            return;
        }
        Optional<Location> location =  locationService.getLocationById(locationId);
        if (location.isPresent()) {
            LOGGER.info("Location exists, it can be changed for requested event.");
        } else {
            LOGGER.warning("Event cannot be updated with an nonexistent location.");
            throw new NoLocationWithRequestedIdFoundException(NO_LOCATION_WITH_ID_REQUESTED_FOUND, locationId);
        }
        //todo validate no tickets
    }


    @Override
    public List<Event> getEventsSorted() {
        LOGGER.info("Event Service getEvents called.");
        var events = eventRepository.getEvents();
        Collections.sort(events);
        return events;
    }
}
