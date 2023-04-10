package exceptions;

public class InvalidEventsListException extends RuntimeException {
    public InvalidEventsListException(String message) {
        super(message);
    }
}
