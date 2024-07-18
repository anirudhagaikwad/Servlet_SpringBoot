package signupin;

import java.sql.*;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";

    public UserDAO() {
        try {
            // Load the H2 database driver class
            Class.forName("org.h2.Driver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load H2 driver", e);
        }
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "email VARCHAR(255) NOT NULL UNIQUE," +
                         "password VARCHAR(255) NOT NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(String email, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (email, password) VALUES (?, ?)")) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
