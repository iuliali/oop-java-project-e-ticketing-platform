package exceptions;

public class EventDoesNotHaveRequestedCategoryException extends RuntimeException {

    public EventDoesNotHaveRequestedCategoryException(String message) {
        super(message);
    }
}
