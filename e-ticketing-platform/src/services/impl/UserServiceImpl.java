package services.impl;

import enums.TicketCategory;
import exceptions.UserNameAlreadyExistsException;
import exceptions.UserNameNotFoundException;
import models.Event;
import models.TicketEvent;
import models.User;
import repositories.UserRepository;
import services.TicketService;
import services.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static constants.Constants.USERNAME_ALREADY_EXISTS;
import static constants.Constants.USERNAME_NOT_FOUND;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;

    public UserServiceImpl(TicketService ticketService) {
        this.userRepository = new UserRepository();
        this.ticketService = ticketService;
    }

    public void  registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) {
        try {
            Optional<User> user = getUserByUserName(userName);
            if (user.isPresent()) {
                throw new UserNameAlreadyExistsException(USERNAME_ALREADY_EXISTS);
            }
        } catch (UserNameAlreadyExistsException exception) {
            System.out.println("User " + userName + " could not be registered. An exceprion occured: ");
            System.out.println(exception.getMessage());
            return;
        }
        User newUser = new User(userName, birthDate, firstName, lastName);
        userRepository.addUser(newUser);
        System.out.println("User " + userName + " was successfully registered!");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    public void addBoughtTicketUser(User user, TicketEvent ticket) {
        user.addTicket(ticket);
    }
    public Optional<TicketEvent> buyTicket(String userName, Event event, TicketCategory category) {
        User user;
        try {
            user = getUserByUserName(userName).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, userName)
            );
        } catch (UserNameNotFoundException exception) {
            System.out.println("Buying ticket failed!");
            System.out.println(exception.getMessage());
            return Optional.empty();
        }
        TicketEvent ticket = ticketService.getAvailableTicket(event, category);
        ticketService.addTicket(ticket, event);
        addBoughtTicketUser(user, ticket);
        System.out.println("Ticket was successfully bought!");
        return Optional.ofNullable(ticket);

    }
}
