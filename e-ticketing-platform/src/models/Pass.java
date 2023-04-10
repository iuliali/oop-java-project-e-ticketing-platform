package models;

import enums.LocationType;
import enums.TicketCategory;

public class Pass extends TicketEvent {

    public Pass(Event event) {
        super(event, TicketCategory.PASS);
    }

    public Pass(TicketEvent otherTicket, LocationType place) {
        super(otherTicket);
    }
}
