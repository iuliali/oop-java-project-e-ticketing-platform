package services.impl;

import models.User;
import services.CSVReaderWriterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static constants.Constants.DELIMITER;
import static constants.Constants.USER_FILE_NAME;

public class UserCSVReaderWriterServiceImpl implements CSVReaderWriterService<User> {
    private static UserCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = USER_FILE_NAME;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static UserCSVReaderWriterServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserCSVReaderWriterServiceImpl();
        }
        return INSTANCE;
    }

    private UserCSVReaderWriterServiceImpl() {
    }

    @Override
    public String objectToCSV(User ob) {
        return ob.getId() + DELIMITER +
                ob.getUserName() + DELIMITER
                + ob.getBirthDate().format(formatter) + DELIMITER
                + ob.getFirstName() + DELIMITER
                + ob.getLastName();
    }
    @Override
    public User processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        String dateString = split[2];
        LocalDateTime birthDate = LocalDateTime.parse(dateString, formatter);
        return new User(Integer.parseInt(split[0]), split[1], birthDate, split[3], split[4]);
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }


}
