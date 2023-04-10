package repositories;

import models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        //TODO:ADD EXCEPTION
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
