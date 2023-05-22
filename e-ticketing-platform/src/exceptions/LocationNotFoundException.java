package exceptions;

import models.Location;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException (String message) {
        super(message);
    }
}
