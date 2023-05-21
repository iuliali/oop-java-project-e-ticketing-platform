package services;

import enums.TicketCategory;
import models.Event;
import models.TicketEvent;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketEvent> getTickets();
    Optional<TicketEvent> getTicketById(Integer id);
    void addTicket(TicketEvent ticket);
    TicketEvent getAvailableTicket(Event event, TicketCategory category);

    void deleteTicket(Integer id);
}
