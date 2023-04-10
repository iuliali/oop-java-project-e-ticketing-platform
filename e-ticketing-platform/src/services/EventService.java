package services;

import models.Event;

import javax.swing.*;
import java.util.List;

public interface EventService {
    void addEvent(Event event);

    List<Event> getEventsSorted();

    List<Event> getEvents(boolean sorted);
}
