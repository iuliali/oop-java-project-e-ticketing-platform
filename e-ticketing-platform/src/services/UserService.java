package services;

import enums.TicketCategory;
import models.Event;
import models.TicketEvent;
import models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    public void addBoughtTicketUser(User user, TicketEvent ticket) ;
    public Optional<TicketEvent> buyTicket(String userName, Event event, TicketCategory category) ;

    public void registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) ;
    public List<User> getUsers();
    public Optional<User> getUserByUserName(String username);
}
