package exceptions;

public class FirstArgumentMustBeAMultiDayEventException extends RuntimeException {

    public FirstArgumentMustBeAMultiDayEventException(String message) {
        super(message);
    }
}
