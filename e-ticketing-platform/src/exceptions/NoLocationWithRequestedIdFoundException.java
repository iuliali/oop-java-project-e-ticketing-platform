package exceptions;

public class NoLocationWithRequestedIdFoundException extends RuntimeException {
    public NoLocationWithRequestedIdFoundException(String message, Integer id) {
        super(message + id);
    }
}
