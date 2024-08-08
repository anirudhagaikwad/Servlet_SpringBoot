package signupin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/userdb?autoReconnect=true&useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO Users (email, password) VALUES (?, ?);";
    private static final String SELECT_USER_BY_EMAIL = "SELECT id, email, password FROM Users WHERE email = ?";

    public UserDAO() {
        try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL driver.", e);
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addUser(String email, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");
                user = new User(id, userEmail, userPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
