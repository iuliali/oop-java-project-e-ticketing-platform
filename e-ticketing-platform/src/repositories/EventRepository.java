package repositories;

import exceptions.NoEventInListException;
import models.Event;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.EVENT_LIST_EMPTY;

public class EventRepository {
    private List<Event> events;

    public EventRepository() {
        this.events = new ArrayList<>();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        if(events ==  null | events.isEmpty()) {
            throw new NoEventInListException(EVENT_LIST_EMPTY);
        } else if (!events.contains(event)){

        }
    }
}
