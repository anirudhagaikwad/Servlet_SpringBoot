package builder;
/* DatabaseConnection Class with Builder
By using the Builder pattern, you can create and configure complex objects in a more readable and flexible way, 
separating the construction logic from the representation.
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url;
    private String user;
    private String passwd;
    private Connection connection;

    // Private constructor
    private DatabaseConnection(Builder builder) {
        this.url = builder.url;
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.connection = builder.connection;
    }

    // Getters
    public Connection getConnection() {
        return connection;
    }

    // Static nested Builder class
    public static class Builder {
        private String url;
        private String user;
        private String passwd;
        private Connection connection;

        // Setters with method chaining
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setPasswd(String passwd) {
            this.passwd = passwd;
            return this;
        }

        public DatabaseConnection build() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                this.connection = DriverManager.getConnection(url, user, passwd);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return new DatabaseConnection(this);
        }
    }
}


/*
The DatabaseConnection class has a private constructor that accepts a Builder object.
The Builder class is a static nested class with methods to set connection parameters (url, user, passwd) and a build() method to create the Connection object.
*/

