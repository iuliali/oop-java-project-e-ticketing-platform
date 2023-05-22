package services.impl;

import dbconfig.DatabaseConfiguration;
import dtos.UserDto;
import enums.TicketCategory;
import exceptions.EventDoesNotHaveRequestedCategoryException;
import exceptions.NoUserException;
import exceptions.UserNameAlreadyExistsException;
import exceptions.UserNameNotFoundException;
import models.Event;
import models.TicketEvent;
import models.User;
import repositories.UserRepository;
import services.TicketService;
import services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;

        public UserServiceImpl(TicketService ticketService, DatabaseConfiguration databaseConfiguration) {
        this.userRepository = new UserRepository(databaseConfiguration);
        this.ticketService = ticketService;
        LOGGER.info("User Service created.");
    }

    public void  registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) {
        try {
            Optional<User> user = getUserByUserName(userName);
            if (user.isPresent()) {
                throw new UserNameAlreadyExistsException(USERNAME_ALREADY_EXISTS);
            }
        } catch (UserNameAlreadyExistsException exception) {
            LOGGER.warning("User " + userName + " could not be registered. An exception occurred: "
                    + exception.getMessage());
            return;
        }
        User newUser = new User(userName, birthDate, firstName, lastName);
        userRepository.addUser(newUser);
        LOGGER.info("User %s id :%d was successfully registered.".formatted(userName, newUser.getId()));

    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        LOGGER.info("User Service method getUsers() called");
        try {
            users = userRepository.getUsers();
            for(User user: users) {
                List<TicketEvent> tickets = ticketService.getSoldTicketsByUserId(user.getId());
                user.setBoughtTickets(tickets);
            }
            if (users.isEmpty()) {
                throw new NoUserException(NO_USER_EXCEPTION_MESSAGE);
            }
        } catch (RuntimeException e) {
            LOGGER.warning("Exception occurred: "+ e.getMessage());
        }
        return users;
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
            LOGGER.info("User Service method getUserByUserName(\"%s\") called".formatted(userName));
            return userRepository.getUserByUserName(userName);
    }

    @Override
    public void editUser(Integer id, UserDto editedUser) {
        LOGGER.info("User Service method editUser(%d) called".formatted(id));
        userRepository.editUser(id, editedUser);
        LOGGER.info("Succesfully edited user with id : %d".formatted(id));
    }

    @Override
    public void deleteUser(Integer id) {
        LOGGER.info("User Service method deleteUser(%d) called".formatted(id));
        userRepository.deleteUser(id);
        LOGGER.info("Succesfully deleted user with id : %d".formatted(id));
    }

    public void addBoughtTicketUser(User user, TicketEvent ticket) {
        user.addTicket(ticket);
    }
    public Optional<TicketEvent> buyTicket(String userName, Event event, TicketCategory category) {
        User user;
        TicketEvent ticket;
        try {
            user = getUserByUserName(userName).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, userName)
            );
            ticket = ticketService.getAvailableTicket(event, category);
            ticket.setUser(user);

        } catch (UserNameNotFoundException | EventDoesNotHaveRequestedCategoryException exception) {

            LOGGER.warning("Buying ticket failed. "
                    + exception.getMessage());
            return Optional.empty();
        }
        ticketService.addTicket(ticket);
        addBoughtTicketUser(user, ticket);
        LOGGER.info("Ticket for event " + ticket.getEvent()+" id :"+ ticket.getId() + " was successfully bought.");
        return Optional.of(ticket);

    }

    @Override
    public void returnTicket(String userName, Integer ticketId) {
        User user;
        try {
            user = getUserByUserName(userName).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, userName)
            );
        } catch (UserNameNotFoundException exception) {

            LOGGER.warning("Returning ticket failed. "
                    + exception.getMessage());
            return;
        }
        ticketService.deleteTicket(ticketId);
        LOGGER.info("User: " +userName +" returned ticketwith id :"+ ticketId + " successfully.");

    }
}
