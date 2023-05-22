package util;

import exceptions.LoggerConfigException;

import java.io.IOException;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static constants.Constants.*;

public class LoggerUtils {


    public static Logger getLogger(String className) {

        Logger logger = Logger.getLogger(className);
        try {
            FileHandler fileHandler = new FileHandler(CSV_FILE_NAME);
            FileHandler fileHandlerLog = new FileHandler(LOGS_FILE_NAME);
            SimpleFormatter formatter = new SimpleFormatter() {
                private static final String format = "%1$tF %1$tT, %2$-7s, %3$s %n";
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            };

            fileHandler.setFormatter(formatter);
            fileHandlerLog.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.addHandler(fileHandlerLog);


        } catch (IOException e) {
            throw new LoggerConfigException(LOGGER_CONFIG_EXCEPTION.formatted(e.getMessage()));
        }
        return logger;
    }

}