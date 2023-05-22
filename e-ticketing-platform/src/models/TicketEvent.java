package models;

import enums.TicketCategory;

public class TicketEvent {
    private static Integer idGenerator = 0;
    private final Integer id;
    private TicketCategory ticketCategory;
    private Event event;
    private User user;

    public TicketEvent(Integer id, TicketCategory ticketCategory, int eventId, int userId) {
        this.id = id;
        this.ticketCategory = ticketCategory;
        this.event = new Event(eventId);
        this.user = new User(userId);
    }

    public TicketEvent(int id, TicketCategory ticketCategory, int eventId, User user) {
        this.id = id;
        this.ticketCategory = ticketCategory;
        this.event = new Event(eventId);
        this.user = user;
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


    @Override
    public String toString() {
        return "TicketEvent{" +
                "id=" + id +
                ", ticketCategory=" + ticketCategory + '\n'+
                ", event=" + event.getName()+ " at Location: "+ event.getLocation().getName() + '\n'+
                ", user=" + user.getUserName() +
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
