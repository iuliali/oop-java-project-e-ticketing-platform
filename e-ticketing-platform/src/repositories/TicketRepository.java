package repositories;

import dbconfig.DatabaseConfiguration;
import enums.TicketCategory;
import exceptions.DBException;
import models.TicketEvent;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.DB_EXCEPTION;
import static constants.Constants.LOGGER;
import static constants.StatementsString.*;

public class TicketRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public TicketRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<TicketEvent> getSoldTickets() {
        List<TicketEvent> tickets = new ArrayList<>();
        try (PreparedStatement statement = databaseConfiguration
                .getConnection()
                .prepareStatement(QUERY_GET_ALL_TICKETS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                TicketEvent ticket = new TicketEvent (
                        resultSet.getInt("id"),
                        TicketCategory.valueOf(resultSet.getString("ticketCategory")),
                        resultSet.getInt("eventId"),
                        new User(
                                resultSet.getInt(11),
                                resultSet.getString(12),
                                LocalDateTime.of(resultSet.getDate(13).toLocalDate(), LocalTime.MIDNIGHT),
                                resultSet.getString(14),
                                resultSet.getString(15)
                        ));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, TicketRepository.class);
        }
        return tickets;
    }


    public void addTicket(TicketEvent ticket) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_TICKET)) {
            statement.setString(1, ticket.getTicketCategory().name());
            statement.setInt(2, ticket.getEvent().getId());
            statement.setInt(3, ticket.getUser().getId());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, TicketRepository.class);
        }

    }

    public Optional<TicketEvent> getTicketById(Integer id) {
        TicketEvent ticket = null;
        try (PreparedStatement statement = databaseConfiguration
                .getConnection()
                .prepareStatement(QUERY_TICKET_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                ticket = new TicketEvent (
                        resultSet.getInt("id"),
                        TicketCategory.valueOf(resultSet.getString("ticketCategory")),
                        resultSet.getInt("eventId"),
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("userName"),
                                LocalDateTime.of(resultSet.getDate("birthDate").toLocalDate(), LocalTime.MIDNIGHT),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName")
                        ));
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, TicketRepository.class);
        }
        return Optional.ofNullable(ticket);
    }

    public void deleteTicket(Integer id) {

        try (PreparedStatement statement = databaseConfiguration
                .getConnection()
                .prepareStatement(DELETE_TICKET_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, TicketRepository.class);
        }
        LOGGER.info("Ticket with id=%d deleted successfully".formatted(id));

    }
}
