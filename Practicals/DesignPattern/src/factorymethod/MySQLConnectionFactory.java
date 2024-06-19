package factorymethod;
//Implement the Factory Interface:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionFactory implements ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/practical?autoReconnect=true&SSL=false";
    private static final String USER = "root";
    private static final String PASSWD = "";

    @Override
    public Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

