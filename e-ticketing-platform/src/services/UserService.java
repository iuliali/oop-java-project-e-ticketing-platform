package services;

import enums.TicketCategory;
import models.Event;
import models.TicketEvent;
import models.User;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    public void addBoughtTicketUser(User user, TicketEvent ticket) ;
    public TicketEvent buyTicket(User user, Event event, TicketCategory category) ;

    public void registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) ;
    public List<User> getUsers();
    public User getUserByUserName(String username);
}
