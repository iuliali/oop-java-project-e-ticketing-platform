package dbconfig;

import exceptions.DBException;
import util.FileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static constants.Constants.*;

public class DatabaseConfiguration {

    private final Connection databaseConnection;

    public DatabaseConfiguration() throws RuntimeException {
        try {
            String[] dbInfo = FileUtils.readDbInfoFromCsv(DB_INFO_FILE_NAME);
            String url = dbInfo[0];
            String username = dbInfo[1];
            String pass = dbInfo[2];
            databaseConnection = DriverManager.getConnection(url, username, pass);
        } catch (IOException | SQLException e) {
            LOGGER.warning(e.getMessage());
            throw new DBException(DB_EXCEPTION, DatabaseConfiguration.class);
        }
    }

    public Connection getConnection(){
        return databaseConnection;
    }


}