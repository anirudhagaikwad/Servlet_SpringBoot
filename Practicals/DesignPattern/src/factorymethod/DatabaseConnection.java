package factorymethod;
//Create a Singleton for Connection Management

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private static ConnectionFactory factory = new MySQLConnectionFactory();

    private DatabaseConnection() {
        connection = factory.createConnection();
    }

    public static Connection getConnection() {
        if (connection == null) {
            new DatabaseConnection();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

