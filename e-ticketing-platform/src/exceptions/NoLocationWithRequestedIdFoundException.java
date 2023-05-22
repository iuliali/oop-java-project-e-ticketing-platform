package exceptions;

public class NoLocationWithRequestedIdFoundException extends RuntimeException {
    public NoLocationWithRequestedIdFoundException(String message) {
        super(message);
    }
}
