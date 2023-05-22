package services;

import dtos.EventDto;
import models.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    void addEvent(Event event);

    List<Event> getEventsSorted();

    List<Event> getEvents(boolean sorted);
    void deleteEvent(Integer id);
    void editEvent(Integer id, EventDto editedDto);
   Optional<Event> getEventById(Integer id);

    }
