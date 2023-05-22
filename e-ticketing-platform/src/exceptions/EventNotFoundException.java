package exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message, Integer id) {
        super(message + id);
    }
}
