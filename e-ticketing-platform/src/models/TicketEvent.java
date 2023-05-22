package models;

import enums.TicketCategory;

public class TicketEvent {
    private static Integer idGenerator = 0;
    private final Integer id;
    private TicketCategory ticketCategory;
    private Event event;

    private User user;



    @Override
    public String toString() {
        return "TicketEvent {\n" +
                "\tid=" + id + ", \n" +
                "\tevent:" + event.getName()+ " at "+ event.getLocation().getName() +", \n" +
                "\tticketCategory=" + ticketCategory.getName() + "\n" +
                '}';
    }
    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public static void setIdGenerator(Integer idGenerator) {
        TicketEvent.idGenerator = idGenerator;
    }

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

    public TicketEvent(Event event, TicketCategory category, User user) {
        this.user = user;
        this.id = ++idGenerator;
        this.event = event;
        this.ticketCategory = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TicketEvent(Event event, TicketCategory category) {
        this.id = ++idGenerator;
        this.event = event;
        this.ticketCategory = category;
    }
    public TicketEvent(Integer id, Integer eventId, TicketCategory category, User user) {
        this.id = id;
        this.event = new Event(eventId);
        this.ticketCategory = category;
        this.user = user;
    }
    public TicketEvent(Integer id, Integer eventId, TicketCategory category) {
        this.id = id;
        this.event = new Event(eventId);
        this.ticketCategory = category;
    }
    public TicketEvent(TicketEvent otherTicket) {
        this.id = ++idGenerator;
        this.event = otherTicket.event;

    }

}
