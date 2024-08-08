package signupin;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/userdb?autoReconnect=true&useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("MySQL driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load MySQL driver.", e);
            throw new RuntimeException("Failed to load MySQL driver.", e);
        }
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "email VARCHAR(255) NOT NULL UNIQUE," +
                         "password VARCHAR(255) NOT NULL)";
            statement.executeUpdate(sql);
            logger.info("Table Users created or already exists.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to create table in MySQL.", e);
            throw new RuntimeException("Failed to create table in MySQL.", e);
        }
    }

    public User getUserByEmail(String email) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve user by email.", e);
        }
        return null;
    }

    public void addUser(String email, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (email, password) VALUES (?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
            logger.info("User added successfully.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to add user.", e);
        }
    }
}
