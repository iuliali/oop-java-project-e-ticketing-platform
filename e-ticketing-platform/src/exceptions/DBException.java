package exceptions;

import java.lang.reflect.Type;

public class DBException extends RuntimeException {
    public DBException(String message, Type issuedInClass) {
        super(message + " " + "issued in class :" +issuedInClass);
    }
}
