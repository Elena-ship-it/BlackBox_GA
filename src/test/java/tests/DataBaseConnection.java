package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL =
            "jdbc:postgresql://ep-late-sun-audf596r-pooler.c-10.us-east-1.aws.neon.tech:5432/neondb?";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_SMgsJj56dVqm";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
