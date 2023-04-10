package exceptions;

public class TicketsAlreadySoldOutException extends RuntimeException {

    public TicketsAlreadySoldOutException (String message) {
        super(message);
    }

}
