package repositories;

import exceptions.NoEventInListException;
import models.Event;
import models.Location;
import models.MapEventTicketsConfiguration;
import services.impl.EventCSVReaderWriterServiceImpl;
import services.impl.MapEventTicketCSVReaderWriterServiceImpl;

import java.util.List;
import java.util.Optional;

import static constants.Constants.EVENT_LIST_EMPTY;

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
            Location location = locations.stream().filter(l -> l.getId() == event.getLocation().getId()).findFirst().orElseThrow(
                    //todo
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
        return events;
    }

    public void addEvent(Event event) {
        //first add configs
        for (MapEventTicketsConfiguration configuration: event.getTicketsAvailable()) {
            configuration.setEventId(event.getId());
            this.configurations.add(configuration);
            this.eventConfigReaderWriterService.write(configuration);
        }
        //then add event
        this.events.add(event);
        this.csvReaderWriterService.write(event);
    }

    public Optional<Event> getEventById(Integer id) {
        return this.events.stream().filter(e -> e.getId() ==  id).findFirst();
    }

    public void deleteEvent(Integer id) {
        if(events ==  null || events.isEmpty()) {
            throw new NoEventInListException(EVENT_LIST_EMPTY);
        }
        Event event = getEventById(id).orElseThrow(
                //todo
        );
        //delete configs
        this.configurations.removeAll(event.getTicketsAvailable());
        this.events.remove(event);
    }
}
