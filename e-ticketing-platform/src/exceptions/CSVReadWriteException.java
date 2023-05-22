package exceptions;

public class CSVReadWriteException extends RuntimeException {
    public CSVReadWriteException(String message) {
        super(message);
    }
}
