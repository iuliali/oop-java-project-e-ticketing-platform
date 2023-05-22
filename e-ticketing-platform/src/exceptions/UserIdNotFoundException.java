package exceptions;

public class UserIdNotFoundException extends  RuntimeException {
    public UserIdNotFoundException(String message, Integer id) {
        super(message + id);
    }

}
