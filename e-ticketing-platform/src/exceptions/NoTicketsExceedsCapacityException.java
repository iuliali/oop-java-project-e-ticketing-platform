package exceptions;

public class NoTicketsExceedsCapacityException extends RuntimeException {
    public NoTicketsExceedsCapacityException(String message) {
        super(message);
    }
}
