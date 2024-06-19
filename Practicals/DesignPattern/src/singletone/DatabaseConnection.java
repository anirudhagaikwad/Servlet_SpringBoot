package singletone;
/* implement the Singleton pattern for the Connection object */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/practical?autoReconnect=true&SSL=false";
    private static final String USER = "root";
    private static final String PASSWD = "";

    private static Connection connection = null;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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

/*
The DatabaseConnection class uses the Singleton pattern to ensure only one instance of the database connection is created.
The constructor initializes the connection.
The getConnection() method returns the singleton instance of the connection.

The Singleton pattern is a design pattern that ensures a class has only one instance and provides a global point of access to that instance.

##Single Instance:
Only one instance of the class is created.
This instance is shared throughout the application.

##Private Constructor:
The constructor is private to prevent direct instantiation from outside the class.
This ensures that the class cannot be instantiated more than once.

##Static Method:
A public static method provides access to the single instance.
This method checks if the instance exists and creates it if it does not.
*/