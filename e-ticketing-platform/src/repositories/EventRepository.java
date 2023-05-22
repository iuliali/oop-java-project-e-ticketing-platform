package repositories;

import exceptions.EventNotFoundException;
import exceptions.LocationNotFoundException;
import exceptions.NoEventInListException;
import models.Event;
import models.Location;
import models.MapEventTicketsConfiguration;
import services.impl.EventCSVReaderWriterServiceImpl;
import services.impl.MapEventTicketCSVReaderWriterServiceImpl;

import java.util.List;
import java.util.Optional;

import static constants.Constants.*;

public class EventRepository {
    private  List<Event> events;
    private List<MapEventTicketsConfiguration> configurations;
    public final EventCSVReaderWriterServiceImpl
            csvReaderWriterService = EventCSVReaderWriterServiceImpl.getInstance();
    public final MapEventTicketCSVReaderWriterServiceImpl
            eventConfigReaderWriterService = MapEventTicketCSVReaderWriterServiceImpl.getInstance();


    public EventRepository(List<Location> locations) {
        this.configurations = this.eventConfigReaderWriterService.read();
        this.events = this.csvReaderWriterService.read();
        for(Event event: this.events) {
            List<MapEventTicketsConfiguration> eventConfig = this.configurations.stream()
                    .filter(config -> config.getEventId() == event.getId()).toList();
            event.setTicketsAvailable(eventConfig);
            Location location = locations.stream().filter(l -> l.getId() == event.getLocation().getId()).findFirst()
                    .orElseThrow(
                            () -> new LocationNotFoundException(
                                    LOCATION_NOT_FOUND.formatted(event.getLocation().getId()))
            );
            event.setLocation(location);
        }
        if (!this.events.isEmpty()) {
            Integer maxId = this.events.stream().map(Event::getId)
                    .reduce(Integer.MIN_VALUE, Integer::max);
            Event.setIdGenerator(maxId + 1);
        }
        if (!this.configurations.isEmpty()) {
            Integer maxId = this.configurations.stream().map(MapEventTicketsConfiguration::getId)
                    .reduce(Integer.MIN_VALUE, Integer::max);
            MapEventTicketsConfiguration.setIdGenerator(maxId + 1);
        }
    }

    public List<Event> getEvents() {
        return csvReaderWriterService.read();
    }

    public void addEvent(Event event) {
        for (MapEventTicketsConfiguration configuration: event.getTicketsAvailable()) {
            configuration.setEventId(event.getId());
            this.configurations.add(configuration);
            this.eventConfigReaderWriterService.write(configuration);
        }
        this.events.add(event);
        this.csvReaderWriterService.write(event);
    }

    public Optional<Event> getEventById(Integer id) {
        return csvReaderWriterService.read().stream().filter(e -> e.getId() ==  id).findFirst();
    }

    public void deleteEvent(Integer id) {
        if(events ==  null || events.isEmpty()) {
            throw new NoEventInListException(EVENT_LIST_EMPTY);
        }
        Event event = getEventById(id).orElseThrow(
                () -> new EventNotFoundException(EVENT_NOT_FOUND)
        );
        this.configurations.removeAll(event.getTicketsAvailable());
        this.events.remove(event);
    }
}
