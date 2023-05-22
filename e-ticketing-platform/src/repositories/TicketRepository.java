package repositories;

import exceptions.EventNotFoundException;
import exceptions.TicketNotFoundException;
import models.Event;
import models.TicketEvent;
import services.impl.TicketCSVReaderWriterServiceImpl;

import java.util.*;

import static constants.Constants.EVENT_NOT_FOUND;
import static constants.Constants.TICKET_NOT_FOUND;

public class TicketRepository {
    private List<TicketEvent> soldTickets;
    private final TicketCSVReaderWriterServiceImpl csvReaderWriterService = TicketCSVReaderWriterServiceImpl.getInstance();

    public TicketRepository(List<Event> events) {
        this.soldTickets =  this.csvReaderWriterService.read();
        for (TicketEvent ticket: this.soldTickets) {
            Event event = events.stream().filter(e -> e.getId() == ticket.getEvent().getId()).findAny().orElseThrow(
                    () -> new EventNotFoundException(EVENT_NOT_FOUND.formatted(ticket.getEvent().getId()))
            );
            ticket.setEvent(event);
        }
        if (!this.soldTickets.isEmpty()) {
            Integer maxId = this.soldTickets.stream().map(TicketEvent::getId)
                    .reduce(Integer.MIN_VALUE, Integer::max);
            TicketEvent.setIdGenerator(maxId + 1);
        }
    }

    public List<TicketEvent> getSoldTickets() {
        return this.soldTickets;
    }


    public void addTicket(TicketEvent ticket) {

        if(soldTickets == null) {
            this.soldTickets = new ArrayList<>();
        }
        this.soldTickets.add(ticket);
        this.csvReaderWriterService.write(ticket);

    }

    public Optional<TicketEvent> getTicketById(Integer id) {
        return csvReaderWriterService.read().stream().filter(t -> t.getId() == id).findFirst();
    }

    public void deleteTicket(Integer id) {
        TicketEvent ticket = getTicketById(id).orElseThrow(
                () -> new TicketNotFoundException(TICKET_NOT_FOUND.formatted(id))
        );
        this.soldTickets.remove(ticket);
        this.csvReaderWriterService.writeAll(this.soldTickets);
    }
}
