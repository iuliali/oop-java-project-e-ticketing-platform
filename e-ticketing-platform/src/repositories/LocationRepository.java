package repositories;

import dbconfig.DatabaseConfiguration;
import dtos.LocationDto;
import enums.LocationType;
import exceptions.NoLocationWithRequestedIdFoundException;
import models.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.LOGGER;
import static constants.Constants.NO_LOCATION_WITH_ID_REQUESTED_FOUND;
import static constants.StatementsString.*;


public class LocationRepository {
    private final DatabaseConfiguration databaseConfiguration;

    public LocationRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();

        try (Statement statement = databaseConfiguration.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_LOCATIONS);
            while (resultSet.next()) {
                locations.add(new Location(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("name"),
                        resultSet.getInt("totalCapacity"),
                        LocationType.valueOf(resultSet.getString("type"))
                ));
            }
        }catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return locations;
    }

    public void addLocation(Location location) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_LOCATION)) {
            statement.setString(1, location.getName());
            statement.setString(2, location.getAddress());
            statement.setInt(3, location.getTotalCapacity());
            statement.setString(4, location.getType().name());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public Optional<Location> getLocationById(Integer id) {
        Location location = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(QUERY_LOCATION_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                location = new Location(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("totalCapacity"),
                        LocationType.valueOf(resultSet.getString("type")));
                break;
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return Optional.ofNullable(location);
    }

    public void editLocation(Integer id, LocationDto editedLocation) {
       Location location = getLocationById(id).orElseThrow(
               () -> new NoLocationWithRequestedIdFoundException(NO_LOCATION_WITH_ID_REQUESTED_FOUND)
       );

        updateLocation(location, editedLocation);

        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(UPDATE_LOCATION_BY_ID)) {
            statement.setInt(3, id);
            statement.setString(1, location.getName());
            statement.setString(2, location.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        LOGGER.info("Location with id=%d updated successfully".formatted(id));
    }

    private void updateLocation(Location location, LocationDto editedLocation) {

        if (editedLocation.getName()!= null && !editedLocation.getName().isEmpty()) {
            location.setName(editedLocation.getName());
        }
        if (editedLocation.getAddress()!= null && !editedLocation.getAddress().isEmpty()) {
            location.setAddress(editedLocation.getAddress());
        }
    }

    public void deleteLocation(Integer id) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(DELETE_LOCATION_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        LOGGER.info("Location with id=%d deleted successfully".formatted(id));
    }
}
