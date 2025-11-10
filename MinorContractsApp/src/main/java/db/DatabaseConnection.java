package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/minor_contracts_db";
    private static final String user = "root";
    private static final String password ="root_password";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
