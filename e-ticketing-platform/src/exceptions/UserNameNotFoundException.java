package exceptions;

public class UserNameNotFoundException extends RuntimeException {

    public UserNameNotFoundException(String message, String userName) {
        super(message + userName);
    }
}
