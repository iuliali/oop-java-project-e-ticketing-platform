package exceptions;

import com.mysql.cj.log.Log;

public class LoggerConfigException extends RuntimeException {
    public LoggerConfigException(String message) {
        super(message);
    }
}
