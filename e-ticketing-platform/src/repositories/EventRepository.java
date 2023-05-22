package repositories;

import dbconfig.DatabaseConfiguration;
import enums.EventType;
import enums.LocationType;
import models.Event;
import models.Location;
import models.MapEventTicketsConfiguration;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static constants.Constants.LOGGER;
import static constants.StatementsString.*;

public class EventRepository {
    private final DatabaseConfiguration databaseConfiguration;
    private final MapEventTicketsRepository mapEventTicketsRepository;

    public EventRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.mapEventTicketsRepository = new MapEventTicketsRepository(databaseConfiguration);
    }

    public List<Event> getEvents() {
        return null;
    }

    public void addEvent(Event event) {
        Integer eventId = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_EVENT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, event.getName());
            statement.setInt(2, event.getLocation().getId());
            statement.setDate(3, Date.valueOf(event.getStartDate().toLocalDate()));
            statement.setDate(4, Date.valueOf(event.getEndDate().toLocalDate()));
            statement.setString(5, event.getEventType().name());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                eventId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        for (MapEventTicketsConfiguration configuration: event.getTicketsAvailable()) {
            configuration.setEventId(eventId);
            mapEventTicketsRepository.addMapEventTickets(configuration);
        }
    }

    public Optional<Event> getEventById(Integer id) {
        Event event = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(QUERY_EVENT_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                event = new Event (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Location(resultSet.getString("name"),
                                resultSet.getString("address"),
                                resultSet.getInt("totalCapacity"),
                                LocationType.valueOf(resultSet.getString("type"))
                                ),
                       LocalDateTime.of(resultSet.getDate("startDate").toLocalDate(), LocalTime.MIDNIGHT),
                        LocalDateTime.of(resultSet.getDate("endDate").toLocalDate(), LocalTime.MIDNIGHT),
                        EventType.valueOf(resultSet.getString("eventType")));
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return Optional.ofNullable(event);
    }

    public void deleteEvent(Integer id) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(DELETE_EVENT_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        LOGGER.info("eVENT with id=%d deleted successfully".formatted(id));
    }
}
