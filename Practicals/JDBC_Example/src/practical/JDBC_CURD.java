package practical;
/* Practical: Write a program to insert and retrieve the data from database using JDBC */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_CURD {
    private final String USER = "root";
    private final String URL = "jdbc:mysql://localhost:3306/practical?autoReconnect=true&SSL=false";
    private final String PASSWD = "";

    void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void createTbl() {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query = "CREATE TABLE IF NOT EXISTS stdRegistration (stdID INTEGER NOT NULL, stdName VARCHAR(255))";
            Statement stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertData(int stdID, String stdName) {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query = "INSERT INTO stdRegistration (stdID, stdName) VALUES (" + stdID + ", '" + stdName + "')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void readData() {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query = "SELECT * FROM stdRegistration";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int stdID = rs.getInt("stdID");
                String stdName = rs.getString("stdName");
                System.out.println("ID: " + stdID + ", Name: " + stdName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateData(int stdID, String newStdName) {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query = "UPDATE stdRegistration SET stdName = '" + newStdName + "' WHERE stdID = " + stdID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteData(int stdID) {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query = "DELETE FROM stdRegistration WHERE stdID = " + stdID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBC_CURD obj = new JDBC_CURD();
        obj.createTbl();
        obj.insertData(1, "Anirudha");
        obj.insertData(2, "Dinesh");
        obj.readData();
        obj.updateData(1, "Asmita");
        obj.readData();
        obj.deleteData(2);
        obj.readData();
    }
}
