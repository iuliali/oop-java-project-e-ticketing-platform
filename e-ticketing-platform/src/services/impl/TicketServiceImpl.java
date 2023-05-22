package services.impl;

import dbconfig.DatabaseConfiguration;
import enums.TicketCategory;
import exceptions.EventDoesNotHaveRequestedCategoryException;
import exceptions.EventNotFoundException;
import exceptions.TicketsAlreadySoldOutException;
import models.Event;
import models.MapEventTicketsConfiguration;
import models.TicketEvent;
import repositories.TicketRepository;
import services.EventService;
import services.TicketService;

import java.util.*;
import java.util.stream.Collectors;

import static constants.Constants.*;
import static constants.LogConstants.*;

public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;
    public final EventService eventService;

    public TicketServiceImpl(EventService eventService, DatabaseConfiguration databaseConfiguration) {
        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));
        this.eventService = eventService;
        this.ticketRepository = new TicketRepository(databaseConfiguration);
    }

    @Override
    public List<TicketEvent> getTickets() {
        List<TicketEvent> tickets = new ArrayList<>();
        try{
            List<TicketEvent> ticketsWithoutEvents = ticketRepository.getSoldTickets();
            List<Event> events = eventService.getEvents(false);
            for(TicketEvent ticket : ticketsWithoutEvents) {
                Event event = events.stream().filter(e -> e.getId() == ticket.getEvent().getId())
                        .findFirst()
                        .orElseThrow();
                ticket.setEvent(event);
                tickets.add(ticket);
            }
        } catch (RuntimeException e) {
            LOGGER.warning("Exception occurred while getting tickets" + e.getMessage());
        }
        return tickets;
    }

    @Override
    public Optional<TicketEvent> getTicketById(Integer id) {
        TicketEvent ticketEvent = null;
        try{
            TicketEvent ticketWithoutEvent =  ticketRepository.getTicketById(id).orElseThrow();
            Event event = eventService.getEventById(ticketWithoutEvent.getEvent().getId()).orElseThrow();
            ticketWithoutEvent.setEvent(event);
            ticketEvent = ticketWithoutEvent;
        } catch (RuntimeException e) {
            LOGGER.warning("Exception occurred while getting a ticket By Id" + e.getMessage());
        }
        return Optional.ofNullable(ticketEvent);
    }


    @Override
    public void addTicket(TicketEvent ticket) {
        LOGGER.info(ADD_TICKET);
        ticketRepository.addTicket(ticket);
    }


    @Override
    public TicketEvent getAvailableTicket(Event event, TicketCategory category) {
        LOGGER.info(TICKET_GET_AVAILABLE_TICKET_CALL);
        if (event.getTicketsAvailable().stream().noneMatch(m -> m.getCategory() == category)) {
            throw new EventDoesNotHaveRequestedCategoryException(EVENT_DOES_NOT_HAVE_CATEGORY);
        }
        Integer totalNoTickets = event.getTicketsAvailable().stream()
                .map(MapEventTicketsConfiguration::getQuantity).reduce(0, Integer::sum);
        Integer noSoldTickets = getNoSoldTicketsByEventAndTicketCategory(event, category);
        if (totalNoTickets - noSoldTickets <= 0) {
            throw new TicketsAlreadySoldOutException(TICKETS_ALREADY_SOLD);
        } else {
            return new TicketEvent(event, category);
        }

    }

    @Override
    public List<TicketEvent> getSoldTicketsByEventId(Integer eventId) {
        return getTickets().stream().filter(t -> t.getEvent().getId() == eventId).toList();
    }

    @Override
    public List<TicketEvent> getSoldTicketsByUserId(Integer userId) {
        return getTickets().stream().filter(t -> t.getUser().getId() == userId).toList();
    }

    @Override
    public void deleteTicket(Integer id) {
        LOGGER.info(TICKET_DELETE_TICKET_CALL.formatted(id));
        try {
            ticketRepository.deleteTicket(id);
        } catch (RuntimeException e) {
            LOGGER.warning(DELETE_TICKET_FAILED.formatted(id, e.getMessage()));
        }
    }

    @Override
    public Map<TicketCategory, Integer> searchAvailableTicketsPerEvent(String eventName) {
        Map<TicketCategory, Integer> map = new HashMap<>();
        try {
            Event event = eventService.getEventByName(eventName).orElseThrow(
                    () -> new EventNotFoundException(EVENT_NOT_FOUND_NAME + eventName)
            );
            List<TicketEvent> ticketsSold = getSoldTicketsByEventId(event.getId());
            map = event.getTicketsAvailable()
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    MapEventTicketsConfiguration::getCategory,
                            c-> Math.toIntExact(c.getQuantity() -
                                    ticketsSold
                                            .stream()
                                            .filter(t -> t.getTicketCategory() == c.getCategory()).count())));

        } catch (RuntimeException e) {
            LOGGER.warning(e.getMessage());
        }
        return map;
    }

    private Integer getNoSoldTicketsByEventAndTicketCategory(Event event, TicketCategory category) {
        return Math.toIntExact(getSoldTicketsByEventId(event.getId()).stream()
                .filter(t -> t.getTicketCategory() == category)
                .count());
    }

}
