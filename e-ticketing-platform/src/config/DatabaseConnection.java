package config;

import java.sql.Connection;

public class DatabaseConnection {
    private static Connection databaseConnection = null;
    private static DatabaseConfiguration  databaseConfiguration;
    public static Connection getConnection() {
        if (databaseConnection == null) {
            databaseConfiguration = new DatabaseConfiguration();
            databaseConnection = databaseConfiguration.getConnection();
        }
        return databaseConnection;
    }

}
