package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee?autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void registerUser(User user) throws Exception {
    	 try {
             Class.forName("com.mysql.cj.jdbc.Driver"); // Register Driver
             System.out.println("Register user");
         } catch (ClassNotFoundException e) {
             // Handle class not found exception gracefully
             throw new RuntimeException("MySQL JDBC Driver not found!", e);
         }
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getEmail());
                stmt.executeUpdate();
            }
        }
    }

    @Override
    public User validateLogin(String username, String password) throws Exception {
   	 try {
         Class.forName("com.mysql.cj.jdbc.Driver"); // Register Driver
         System.out.println("Register user");
     } catch (ClassNotFoundException e) {
         // Handle class not found exception gracefully
         throw new RuntimeException("MySQL JDBC Driver not found!", e);
     }
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
                    } else {
                        throw new Exception("Invalid username or password");
                    }
                }
            }
        }
    }
}
