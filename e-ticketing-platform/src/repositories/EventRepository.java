package repositories;

import dbconfig.DatabaseConfiguration;
import dtos.EventDto;
import enums.EventType;
import enums.LocationType;
import exceptions.DBException;
import exceptions.EventNotFoundException;
import models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;
import static constants.StatementsString.*;

public class EventRepository {
    private final DatabaseConfiguration databaseConfiguration;
    private final MapEventTicketsRepository mapEventTicketsRepository;

    public EventRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        this.mapEventTicketsRepository = new MapEventTicketsRepository(databaseConfiguration);
    }

    private List<MapEventTicketsConfiguration> getAllMapsByEventId(Integer eventId) {
        return mapEventTicketsRepository.getAllMapsForEventId(eventId);
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        try (PreparedStatement statement = databaseConfiguration.getConnection()
                .prepareStatement(QUERY_GET_ALL_EVENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Location(
                                resultSet.getInt(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getInt(10),
                                LocationType.valueOf(resultSet.getString(11))
                        ),
                        resultSet.getTimestamp("startDate").toLocalDateTime(),
                        resultSet.getTimestamp("endDate").toLocalDateTime(),
                        EventType.valueOf(resultSet.getString("eventType")));
                event = getDerivedClasses(event);
                events.add(event);
            }

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, EventRepository.class);
        }

        for( Event event: events) {
            event.setTicketsAvailable(getAllMapsByEventId(event.getId()));
        }
        return events;
    }

    public void addEvent(Event event) {
        Integer eventId = null;

        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_EVENT,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, event.getName());
            statement.setInt(2, event.getLocation().getId());
            statement.setTimestamp(3, Timestamp.valueOf(event.getStartDate()));
            statement.setTimestamp(4, Timestamp.valueOf(event.getEndDate()));
            statement.setString(5, event.getEventType().name());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                eventId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, EventRepository.class);
        }

        String queryAdditional = "";
        String additionalColumn = "";
        if (event instanceof Concert) {
            queryAdditional = INSERT_CONCERT;
            additionalColumn = ((Concert) event).getArtistName();
        } else if (event instanceof StandUp) {
            queryAdditional = INSERT_STANDUP;
            additionalColumn = ((StandUp) event).getComedianName();
        } else if (event instanceof Play) {
            queryAdditional = INSERT_PLAY;
            additionalColumn = ((Play) event).getSpecialGuest();
        }
        try (PreparedStatement statement = databaseConfiguration.getConnection()
                .prepareStatement(queryAdditional)) {
            statement.setInt(1, eventId);
            statement.setString(2, additionalColumn);
            statement.execute();

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
                        new Location(
                                resultSet.getInt(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getInt(10),
                                LocationType.valueOf(resultSet.getString(11))
                                ),
                       resultSet.getTimestamp("startDate").toLocalDateTime(),
                        resultSet.getTimestamp("endDate").toLocalDateTime(),
                        EventType.valueOf(resultSet.getString("eventType")));
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, EventRepository.class);
        }
        if (event != null) {
            event = getDerivedClasses(event);
            event.setTicketsAvailable(getAllMapsByEventId(event.getId()));
        }
        return Optional.ofNullable(event);
    }

    private Event getDerivedClasses(Event event) {
        Event newEvent = new Event(event);
        if (event.getEventType() == EventType.CONCERT) {
            try (PreparedStatement statement = databaseConfiguration
                    .getConnection()
                    .prepareStatement(QUERY_CONCERT_BY_EVENT_ID)) {
                statement.setInt(1, event.getId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    newEvent = new Concert (event, resultSet.getString("artistName"));
                }
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
                throw new DBException(DB_EXCEPTION, EventRepository.class);
            }
        } else if(event.getEventType() == EventType.STAND_UP) {
            try (PreparedStatement statement = databaseConfiguration
                    .getConnection()
                    .prepareStatement(QUERY_STANDUP_BY_EVENT_ID)) {
                statement.setInt(1, event.getId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    newEvent = new StandUp (event, resultSet.getString("comedianName"));
                }
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
                throw new DBException(DB_EXCEPTION, EventRepository.class);
            }
        } else if(event.getEventType() == EventType.PLAY) {
            try (PreparedStatement statement = databaseConfiguration
                    .getConnection()
                    .prepareStatement(QUERY_PLAYS_BY_EVENT_ID)) {
                statement.setInt(1, event.getId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    newEvent = new Play (event, resultSet.getString("specialGuest"));
                }
            } catch (SQLException e) {
                LOGGER.warning(e.getMessage());
                throw new DBException(DB_EXCEPTION, EventRepository.class);
            }
        }

        return newEvent;
    }

    public void deleteEvent(Integer id) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(DELETE_EVENT_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, EventRepository.class);
        }
        LOGGER.info("Event with id=%d deleted successfully".formatted(id));
    }

    public void editEvent(Integer id, EventDto editedDto) {

        Event event = getEventById(id).orElseThrow(
                () -> new EventNotFoundException(EVENT_NOT_FOUND + id)
        );
        updateEvent(event, editedDto);

        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(UPDATE_EVENT_BY_ID)) {
            statement.setInt(5, id);
            statement.setString(1, event.getName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getStartDate()));
            statement.setTimestamp(3, Timestamp.valueOf(event.getEndDate()));
            statement.setInt(4, editedDto.getLocationId());

            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION,EventRepository.class);

        }
        LOGGER.info("Event with id=%d updated successfully".formatted(id));
    }

    private void updateEvent(Event event, EventDto editedEvent) {
        if (editedEvent.getName() != null && !editedEvent.getName().isEmpty()) {
            event.setName(editedEvent.getName());
        }
        if (editedEvent.getStartDate() != null) {
            event.setStartDate(editedEvent.getStartDate());
        }
        if (editedEvent.getEndDate() != null) {
            event.setEndDate(editedEvent.getEndDate());
        }
    }

    public Optional<Event> getEventByName(String name) {
        Event event = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection()
                .prepareStatement(QUERY_EVENT_GET_BY_NAME)) {
            statement.setString(1, "%" + name.toUpperCase() +"%");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                event = new Event (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Location(
                                resultSet.getInt(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getInt(10),
                                LocationType.valueOf(resultSet.getString(11))
                        ),
                        resultSet.getTimestamp("startDate").toLocalDateTime(),
                        resultSet.getTimestamp("endDate").toLocalDateTime(),
                        EventType.valueOf(resultSet.getString("eventType")));
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, EventRepository.class);
        }
        if (event != null) {
            event = getDerivedClasses(event);
            event.setTicketsAvailable(getAllMapsByEventId(event.getId()));
        }
        return Optional.ofNullable(event);
    }
}
