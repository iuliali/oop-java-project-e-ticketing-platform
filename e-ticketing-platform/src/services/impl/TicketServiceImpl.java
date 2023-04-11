package services.impl;

import enums.LocationType;
import enums.TicketCategory;
import exceptions.EventDoesNotHaveRequestedCategoryException;
import exceptions.TicketsAlreadySoldOutException;
import models.Event;
import models.TicketEvent;
import models.TicketSeatedEvent;
import repositories.TicketRepository;
import services.EventService;
import services.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static constants.Constants.EVENT_DOES_NOT_HAVE_CATEGORY;
import static constants.Constants.TICKETS_ALREADY_SOLD;

public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;
    public final EventService eventService;


    public TicketServiceImpl(EventService eventService) {
        this.eventService = eventService;
        this.ticketRepository = new TicketRepository();
    }

    @Override
    public Map<Event, List<TicketEvent>> getTickets() {
        return ticketRepository.getSoldTicketsByEvents();
    }

    @Override
    public List<TicketEvent> getTicketById() {
        return null;
    }



    @Override
    public void addTicket(TicketEvent ticket, Event event) {
        ticketRepository.addTicket(ticket, event);
    }
    private int findAvailableSeat(Event event, TicketCategory category) {
        Set<TicketCategory> categories = event.getTicketsAvailable().keySet();
        if (!categories.contains(category)) {
            throw new EventDoesNotHaveRequestedCategoryException(EVENT_DOES_NOT_HAVE_CATEGORY);
        } else {
            int noSoldTickets = getNoSoldTicketsByEventAndTicketCategory(event, category);
            List<TicketCategory> categoriesPriority = categories.stream()
                    .filter(x -> x.getPriority() < category.getPriority()).toList();
            int higherPriorityPlaces = categoriesPriority.size();
            int totalOffset = higherPriorityPlaces + noSoldTickets;
            return totalOffset + 1;
        }
    }

    private int getNoSoldTicketsByEventAndTicketCategory(Event event, TicketCategory category) {
        var soldTickets = ticketRepository.getSoldTicketsByEvent(event).orElse(new ArrayList<>())
                .stream()
                .filter(x->x.getTicketCategory() == category)
                .toList();
        return soldTickets.size();
    }
    @Override
    public TicketEvent getAvailableTicket(Event event, TicketCategory category) {
        Integer totalNoTickets = event.getTicketsAvailable().get(category);
        Integer noSoldTickets = getNoSoldTicketsByEventAndTicketCategory(event, category);
        if (totalNoTickets - noSoldTickets <= 0) {
            throw new TicketsAlreadySoldOutException(TICKETS_ALREADY_SOLD);
        } else {
            TicketEvent ticket = null;
            if (event.getLocation().getType() == LocationType.STAND) {
                ticket = new TicketEvent(event, category);
            } else if (event.getLocation().getType() == LocationType.SEAT) {
                int seatNo = findAvailableSeat(event, category);
                ticket = new TicketSeatedEvent(event,seatNo, category);
            }
            return ticket;
        }

    }

    @Override
    public void showSoldTicketsByEvents() {
        Map<Event, List<TicketEvent>> map= getTickets();
        var mapEntries = map.entrySet().stream().toList();
        for (Map.Entry<Event, List<TicketEvent>> mapEntry : mapEntries) {
            System.out.println((mapEntries.indexOf(mapEntry ) + 1) + ". ");
            System.out.println("Event: " + mapEntry.getKey() + "\n");
            System.out.println("Tickets: " );
            for(TicketEvent ticket: mapEntry.getValue()) {
                System.out.println("id: " + ticket.getId() + " " + ticket.getTicketCategory().getName() + "\n");
            }
            System.out.println();
        }
    }
}
