package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static constants.Constants.LOGS_FILE_NAME;

public class LoggerUtils {


    public static Logger getLogger(String clasName) {

        Logger LOGGER = Logger.getLogger(clasName);
        try {
            FileHandler fileHandler = new FileHandler(LOGS_FILE_NAME);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LOGGER;
    }

}