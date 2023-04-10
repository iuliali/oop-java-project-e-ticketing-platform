package exceptions;

public class DayEventNotInIntervalException extends RuntimeException {

    public DayEventNotInIntervalException(String message) {
        super(message);
    }
}
