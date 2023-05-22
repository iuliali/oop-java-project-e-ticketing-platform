package exceptions;

public class LocationUpdateException extends RuntimeException {
    public LocationUpdateException(String message, Integer oldCapacity, Integer newCapacity) {
        super(message + "Old: " +oldCapacity + " > new: " + newCapacity);
    }
}
