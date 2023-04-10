package services.impl;

import enums.TicketCategory;
import exceptions.UserNameNotFoundException;
import models.Event;
import models.TicketEvent;
import models.User;
import repositories.UserRepository;
import services.TicketService;
import services.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static constants.Constants.USERNAME_NOT_FOUND;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;

    public UserServiceImpl(TicketService ticketService) {
        this.userRepository = new UserRepository();
        this.ticketService = ticketService;
    }

    public void  registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) {
        User newUser = new User(userName, birthDate, firstName, lastName);
        userRepository.addUser(newUser);
        System.out.println("User " + userName + " was successfully registered!");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName).orElseThrow(
                () -> new UserNameNotFoundException(USERNAME_NOT_FOUND)
        );
    }

    public void addBoughtTicketUser(User user, TicketEvent ticket) {
        user.addTicket(ticket);
    }
    public TicketEvent buyTicket(User user, Event event, TicketCategory category) {
        TicketEvent ticket = ticketService.getAvailableTicket(event, category);
        ticketService.addTicket(ticket, event);
        addBoughtTicketUser(user, ticket);
        return ticket;

    }
}
