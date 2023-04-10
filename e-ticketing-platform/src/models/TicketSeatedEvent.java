package models;

import enums.TicketCategory;

public class TicketSeatedEvent extends TicketEvent {
    private int seatNumber;

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TicketSeatedEvent(Event event, int seatNumber, TicketCategory category) {
        super(event, category);
        this.seatNumber = seatNumber;
    }

    public TicketSeatedEvent(TicketEvent otherTicket) {
        super(otherTicket);
    }
}
