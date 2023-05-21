package repositories;

import dtos.UserDto;
import exceptions.NoUserException;
import exceptions.UserIdNotFoundException;
import models.User;
import services.impl.UserCSVReaderWriterServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static constants.Constants.NO_USER_EXCEPTION_MESSAGE;
import static constants.Constants.USER_ID_NOT_FOUND;

public class UserRepository {
    private List<User> users;
    private final UserCSVReaderWriterServiceImpl csvReaderWriterService = UserCSVReaderWriterServiceImpl.getInstance();

    public UserRepository() {
        this.users = this.csvReaderWriterService.read();

        if (!this.users.isEmpty()) {
            Integer maxId = this.users.stream().map(User::getId)
                    .reduce(Integer.MIN_VALUE, Integer::max);
            User.setIdGenerator(maxId + 1);
        }
    }

    public List<User> getUsers() {
        if (users == null) {
            throw new NoUserException(NO_USER_EXCEPTION_MESSAGE);
        }
        return this.users;
    }

    public void addUser(User user) {
        if (users == null) {
            this.users = new ArrayList<>();
        }
        this.users.add(user);
        this.csvReaderWriterService.write(user);
    }

    public Optional<User> getUserByUserName(String userName) {
        return users.stream().filter(x-> Objects.equals(x.getUserName(), userName)).findFirst();
    }

    public Optional<User> getUserById(Integer id) {
        return users.stream().filter(x-> Objects.equals(x.getId(), id)).findFirst();
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
        csvReaderWriterService.writeAll(this.users);
    }

    public void deleteUser(Integer id) {
        User user = getUserById(id).orElseThrow(
                () -> new UserIdNotFoundException(USER_ID_NOT_FOUND, id)
        );
        this.users.remove(user);
        csvReaderWriterService.writeAll(this.users);
    }
}
