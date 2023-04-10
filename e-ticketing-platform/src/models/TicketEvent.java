package models;

import enums.TicketCategory;

public class TicketEvent {
    private static int idGenerator = 0;
    private final int id;
    private Event event;

    @Override
    public String toString() {
        return "TicketEvent {\n" +
                "\tid=" + id + ", \n" +
                "\tevent=" + event +", \n" +
                "\tticketCategory=" + ticketCategory + "\n" +
                '}';
    }
    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    private TicketCategory ticketCategory;
    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        TicketEvent.idGenerator = idGenerator;
    }

    public TicketEvent(Event event, TicketCategory category) {
        this.id = ++idGenerator;
        this.event = event;
        this.ticketCategory = category;

    }
    public TicketEvent(TicketEvent otherTicket) {
        this.id = ++idGenerator;
        this.event = otherTicket.event;

    }

}