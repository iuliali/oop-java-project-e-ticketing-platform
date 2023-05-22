package services;

import dtos.UserDto;
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
    public void returnTicket(String userName, Integer ticketId) ;


    public void registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) ;
    public List<User> getUsers();
    public Optional<User> getUserByUserName(String username);
    public void editUser(Integer id, UserDto editedUser);

    public void deleteUser(Integer id);

    public List<TicketEvent> getAllTicketsByUser(String username);
}
