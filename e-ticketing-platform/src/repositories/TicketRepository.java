package repositories;

import models.Event;
import models.TicketEvent;

import java.util.*;

public class TicketRepository {
    private Map<Event, List<TicketEvent>> soldTickets;

    public TicketRepository() {
        this.soldTickets = new HashMap<>();
    }

    public Map<Event, List<TicketEvent>> getSoldTicketsByEvents() {
        //TODO : add exception
        return this.soldTickets;
    }

    public Optional<List<TicketEvent>> getSoldTicketsByEvent(Event event) {

        return Optional.ofNullable(this.soldTickets.get(event));
    }

    public void addTicket(TicketEvent ticket, Event event) {
        if(soldTickets == null) {
            this.soldTickets = new HashMap<>();
        }
        if (!this.soldTickets.containsKey(event)) {
            this.soldTickets.put(event, new ArrayList<>());
        }
        this.soldTickets.get(event).add(ticket);

    }
}
