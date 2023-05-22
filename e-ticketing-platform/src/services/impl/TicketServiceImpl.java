package services.impl;

import enums.TicketCategory;
import exceptions.EventDoesNotHaveRequestedCategoryException;
import exceptions.TicketNotFoundException;
import exceptions.TicketsAlreadySoldOutException;
import models.Event;
import models.MapEventTicketsConfiguration;
import models.TicketEvent;
import repositories.TicketRepository;
import services.EventService;
import services.TicketService;

import java.util.List;
import java.util.Optional;

import static constants.Constants.*;
import static constants.LogConstants.*;

public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;
    public final EventService eventService;

    public TicketServiceImpl(EventService eventService) {
        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));
        this.eventService = eventService;
        this.ticketRepository = new TicketRepository(eventService.getEvents(false));
    }

    @Override
    public List<TicketEvent> getTickets() {
        return ticketRepository.getSoldTickets();
    }

    @Override
    public Optional<TicketEvent> getTicketById(Integer id) {
        return ticketRepository.getTicketById(id);
    }


    @Override
    public void addTicket(TicketEvent ticket) {
        LOGGER.info(ADD_TICKET);
        ticketRepository.addTicket(ticket);
    }


    @Override
    public TicketEvent getAvailableTicket(Event event, TicketCategory category) {
        LOGGER.info(TICKET_GET_AVAILABLE_TICKET_CALL
                .formatted(event.getName(), category));
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
    public void deleteTicket(Integer id) {
        LOGGER.info(TICKET_DELETE_TICKET_CALL.formatted(id));
        try{
            ticketRepository.deleteTicket(id);
        } catch (TicketNotFoundException exception) {
            LOGGER.warning(DELETE_TICKET_FAILED
                    .formatted(id, exception.getMessage()));
        }
    }

    private Integer getNoSoldTicketsByEventAndTicketCategory(Event event, TicketCategory category) {
        return event.getTicketsAvailable().stream().filter(m->m.getCategory() == category)
                .map(MapEventTicketsConfiguration::getQuantity)
                .reduce(0, Integer::sum);
    }

}
