package services.impl;

import models.User;
import services.CSVReaderWriterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserCSVReaderWriterServiceImpl implements CSVReaderWriterService<User> {
    private static UserCSVReaderWriterServiceImpl INSTANCE = null;
    private static final String FILE_NAME = "C:\\Users\\talpa\\Desktop\\info\\sem_2\\java\\oop-java-project-e-ticketing-platform\\e-ticketing-platform\\src\\resources\\persistence\\user.csv";
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
        return ob.getUserName() + DELIMITER
                + ob.getBirthDate().format(formatter) + DELIMITER
                + ob.getFirstName() + DELIMITER
                + ob.getFirstName();
    }
    @Override
    public User processCSVLine(String line) {
        String[] split = line.split(DELIMITER);
        String dateString = split[1];
        LocalDateTime birthDate = LocalDateTime.parse(dateString, formatter);
        return new User(split[0], birthDate, split[2], split[3]);
    }


    @Override
    public String getFileName() {
        return FILE_NAME;
    }

}
