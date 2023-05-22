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
import static constants.LogConstants.*;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;

        public UserServiceImpl(TicketService ticketService, DatabaseConfiguration databaseConfiguration) {
        this.userRepository = new UserRepository(databaseConfiguration);
        this.ticketService = ticketService;
        LOGGER.info(SERVICE_CREATED.formatted(this.getClass().getName()));
    }

    public void  registerNewUser(String userName, String firstName, String lastName, LocalDateTime birthDate) {
            LOGGER.info(REGISTER_NEW_USER_LOG.formatted(userName,
                    firstName, lastName, birthDate));
            try {
            Optional<User> user = getUserByUserName(userName);
            if (user.isPresent()) {
                throw new UserNameAlreadyExistsException(USERNAME_ALREADY_EXISTS);
            }
        } catch (UserNameAlreadyExistsException exception) {
            LOGGER.warning(REGISTER_USER_EXCEPTION.formatted(userName, exception.getMessage()));
            return;
        }
        User newUser = new User(userName, birthDate, firstName, lastName);
        userRepository.addUser(newUser);
        LOGGER.info(REGISTER_USER_SUCCESSFUL.formatted(userName, newUser.getId()));

    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        LOGGER.info(GET_USERS);
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
            LOGGER.warning(e.getMessage());
        }
        return users;
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
            LOGGER.info(GET_USER_BY_USERNAME.formatted(userName));
            return userRepository.getUserByUserName(userName);
    }

    @Override
    public void editUser(Integer id, UserDto editedUser) {
        LOGGER.info(EDIT_USER_CALL.formatted(id));
        userRepository.editUser(id, editedUser);
        LOGGER.info(EDIT_USER_SUCCESS.formatted(id));
    }

    @Override
    public void deleteUser(Integer id) {
        LOGGER.info(DELETE_USER_CALL.formatted(id));
        userRepository.deleteUser(id);
        LOGGER.info(DELETE_USER_SUCCESS.formatted(id));
    }

    @Override
    public List<TicketEvent> getAllTicketsByUser(String username) {
        List<TicketEvent> tickets = new ArrayList<>();
        try {
            User user = getUserByUserName(username).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, username)
            );
            tickets = ticketService.getSoldTicketsByUserId(user.getId());

        } catch (RuntimeException e) {
            LOGGER.warning(e.getMessage());
        }
        return tickets;
    }

    public void addBoughtTicketUser(User user, TicketEvent ticket) {
        user.addTicket(ticket);
    }
    public Optional<TicketEvent> buyTicket(String userName, Event event, TicketCategory category) {
            LOGGER.info(BUY_TICKET_CALL.formatted(userName, event.getName(), category));
        User user;
        TicketEvent ticket;
        try {
            user = getUserByUserName(userName).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, userName)
            );
            ticket = ticketService.getAvailableTicket(event, category);
            ticket.setUser(user);

        } catch (UserNameNotFoundException | EventDoesNotHaveRequestedCategoryException exception) {

            LOGGER.warning(BUY_TICKET_FAILED.formatted(exception.getMessage()));
            return Optional.empty();
        }
        ticketService.addTicket(ticket);
        addBoughtTicketUser(user, ticket);
        LOGGER.info(BUY_TICKET_SUCCESS.formatted(event.getName()));
        return Optional.of(ticket);

    }

    @Override
    public void returnTicket(String userName, Integer ticketId) {
        LOGGER.info(RETURN_TICKET_CALL.formatted(
                userName, ticketId
        ));
        User user;
        try {
            user = getUserByUserName(userName).orElseThrow(
                    () -> new UserNameNotFoundException(USERNAME_NOT_FOUND, userName)
            );
        } catch (UserNameNotFoundException exception) {

            LOGGER.warning(RETURN_TICKET_FAILED.formatted(exception.getMessage()));
            return;
        }
        ticketService.deleteTicket(ticketId);
        LOGGER.info(RETURN_TICKET_SUCCESS.formatted(ticketId));

    }
}
