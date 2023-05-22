package services.impl;

import dbconfig.DatabaseConfiguration;
import enums.TicketCategory;
import exceptions.EventDoesNotHaveRequestedCategoryException;
import exceptions.TicketsAlreadySoldOutException;
import models.Event;
import models.MapEventTicketsConfiguration;
import models.TicketEvent;
import repositories.TicketRepository;
import services.EventService;
import services.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;

public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;
    public final EventService eventService;

    public TicketServiceImpl(EventService eventService, DatabaseConfiguration databaseConfiguration) {
        LOGGER.info("Ticket Service created;");
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
        ticketRepository.addTicket(ticket);
    }


    @Override
    public TicketEvent getAvailableTicket(Event event, TicketCategory category) {
        LOGGER.info("Ticket Service method getAvailableTicket() called.");
        if (event.getTicketsAvailable().stream().noneMatch(m -> m.getCategory() == category)) {
            throw new EventDoesNotHaveRequestedCategoryException(EVENT_DOES_NOT_HAVE_CATEGORY);
        }
        Integer totalNoTickets = event.getTicketsAvailable().stream()
                .map(MapEventTicketsConfiguration::getQuantity).reduce(0, Integer::sum);
        Integer noSoldTickets = getNoSoldTicketsByEventAndTicketCategory(event, category);
        if (totalNoTickets - noSoldTickets <= 0) {
            LOGGER.info("Tickets already sold out for event: " + event.getName());

            throw new TicketsAlreadySoldOutException(TICKETS_ALREADY_SOLD);
        } else {
            return new TicketEvent(event, category);
        }

    }

    @Override
    public List<TicketEvent> getSoldTicketsByEventId(Integer eventId) {
        return getTickets().stream().filter(t -> t.getUser().getId() == eventId).toList();
    }

    @Override
    public List<TicketEvent> getSoldTicketsByUserId(Integer userId) {
        return getTickets().stream().filter(t -> t.getUser().getId() == userId).toList();
    }

    @Override
    public void deleteTicket(Integer id) {
        ticketRepository.deleteTicket(id);
    }

    private Integer getNoSoldTicketsByEventAndTicketCategory(Event event, TicketCategory category) {
        return Math.toIntExact(getSoldTicketsByEventId(event.getId()).stream()
                .filter(t -> t.getTicketCategory() == category)
                .count());
    }

}
