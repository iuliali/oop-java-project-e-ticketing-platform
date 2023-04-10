package repositories;

import exceptions.NoUserException;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static constants.Constants.NO_USER_EXCEPTION_MESSAGE;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
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
    }

    public Optional<User> getUserByUserName(String userName) {
        return users.stream().filter(x-> Objects.equals(x.getUserName(), userName)).findFirst();
    }
}
