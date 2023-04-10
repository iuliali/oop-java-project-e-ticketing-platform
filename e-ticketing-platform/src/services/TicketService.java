package services;

import enums.TicketCategory;
import models.Event;
import models.TicketEvent;

import java.util.List;
import java.util.Map;

public interface TicketService {
    Map<Event, List<TicketEvent>> getTickets();
    List<TicketEvent> getTicketById();
    void addTicket(TicketEvent ticket, Event event);
    TicketEvent getAvailableTicket(Event event, TicketCategory category);
}
