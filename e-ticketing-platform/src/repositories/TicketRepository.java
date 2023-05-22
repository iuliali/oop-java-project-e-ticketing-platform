package repositories;

import dbconfig.DatabaseConfiguration;
import models.TicketEvent;

import java.util.*;

public class TicketRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public TicketRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<TicketEvent> getSoldTickets() {
        return null;
    }


    public void addTicket(TicketEvent ticket) {



    }

    public Optional<TicketEvent> getTicketById(Integer id) {
        return null;
    }

    public void deleteTicket(Integer id) {

    }
}
