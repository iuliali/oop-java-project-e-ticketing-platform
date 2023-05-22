package repositories;

import dbconfig.DatabaseConfiguration;
import enums.TicketCategory;
import models.MapEventTicketsConfiguration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.Constants.LOGGER;
import static constants.StatementsString.*;

public class MapEventTicketsRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public MapEventTicketsRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }
    public void addMapEventTickets(MapEventTicketsConfiguration configuration) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_MAP)) {
            statement.setString(1, configuration.getCategory().name());
            statement.setInt(2, configuration.getQuantity());
            statement.setInt(3, configuration.getEventId());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public List<MapEventTicketsConfiguration> getAllMapsForEventId(Integer id) {
        List<MapEventTicketsConfiguration> maps = new ArrayList<>();
        try (PreparedStatement statement = databaseConfiguration.getConnection()
                .prepareStatement(QUERY_GET_ALL_MAPS_FOR_EVENT_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maps.add(new MapEventTicketsConfiguration(
                        resultSet.getInt("id"),
                        TicketCategory.valueOf(resultSet.getString("category")),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("eventId")
                ));
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return maps;
    }
}
