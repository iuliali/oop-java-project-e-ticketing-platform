package repositories;

import dbconfig.DatabaseConfiguration;
import dtos.UserDto;
import exceptions.UserIdNotFoundException;
import models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static constants.Constants.*;
import static constants.StatementsString.*;

public class UserRepository {
    private final DatabaseConfiguration databaseConfiguration;
    public UserRepository(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;

    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = databaseConfiguration.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(QUERY_GET_ALL_USERS);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        LocalDateTime.of(resultSet.getDate("birthDate").toLocalDate(), LocalTime.MIDNIGHT),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName")

                ));
            }
        }catch (SQLException e) {
            LOGGER.warning(e.getMessage());

        }
        return users;
    }

    public void addUser(User user) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getUserName());
            statement.setDate(2, Date.valueOf(user.getBirthDate().toLocalDate()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getFirstName());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    public Optional<User> getUserByUserName(String userName) {
        User user = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(QUERY_USER_GET_BY_USERNAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user =  new User(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        LocalDateTime.of(resultSet.getDate("birthDate").toLocalDate(), LocalTime.MIDNIGHT),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
                break;
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> getUserById(Integer id) {
        User user = null;
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(QUERY_USER_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user =  new User(
                        resultSet.getInt("id"),
                        resultSet.getString("userName"),
                        LocalDateTime.of(resultSet.getDate("birthDate").toLocalDate(), LocalTime.MIDNIGHT),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
                break;
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());

        }
        return Optional.ofNullable(user);
    }

    private void updateUser(User user, UserDto editedUser) {
        if (editedUser.getUserName()!= null && !editedUser.getUserName().isEmpty()) {
            user.setUserName(editedUser.getUserName());
        }
        if (editedUser.getFirstName()!= null && !editedUser.getFirstName().isEmpty()) {
            user.setFirstName(editedUser.getFirstName());
        }
        if (editedUser.getLastName()!= null &&!editedUser.getLastName().isEmpty()) {
            user.setLastName(editedUser.getLastName());
        }
        if (editedUser.getBirthDate() != null) {
            user.setBirthDate(editedUser.getBirthDate());
        }

    }

    public void editUser(Integer id, UserDto editedUser) {
        User user = getUserById(id).orElseThrow(
                () -> new UserIdNotFoundException(USER_ID_NOT_FOUND, id)
        );
        updateUser(user, editedUser);

        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setInt(5, id);
            statement.setString(1, user.getUserName());
            statement.setDate(2, Date.valueOf(user.getBirthDate().toLocalDate()));
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());


            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());

        }
        LOGGER.info("User with id=%d updated successfully".formatted(id));
    }

    public void deleteUser(Integer id) {
        try (PreparedStatement statement = databaseConfiguration.getConnection().prepareStatement(DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());

        }
        LOGGER.info("User with id=%d deleted successfully".formatted(id));
    }
}
