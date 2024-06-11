package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseTable {

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Consider externalizing this for security

    // Table creation SQL statement
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "username VARCHAR(50) NOT NULL,"
            + "password VARCHAR(50) NOT NULL,"
            + "email VARCHAR(100) NOT NULL"
            + ")";

    public static void createTbl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Register Driver
            System.out.println("Register");
        } catch (ClassNotFoundException e) {
            // Handle class not found exception gracefully
            throw new RuntimeException("MySQL JDBC Driver not found!", e);
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            // Create table
            statement.executeUpdate(CREATE_TABLE_SQL);
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
