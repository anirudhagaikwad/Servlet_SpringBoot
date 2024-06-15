package practical;
/* Practical: Write a program to demonstrate the use of Row interface with all its types */ 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.Predicate;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class RowSetDemo {
    private final String USER = "root";
    private final String URL = "jdbc:mysql://localhost:3306/practical?autoReconnect=true&SSL=false";
    private final String PASSWD = "";

    // Register the JDBC driver
    void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Create tables for demonstration
    void createTbl() {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String query1 = "CREATE TABLE IF NOT EXISTS stdRegistration (stdID INTEGER NOT NULL, stdName VARCHAR(255))";
            String query2 = "CREATE TABLE IF NOT EXISTS stdDetails (stdID INTEGER NOT NULL, stdAge INTEGER)";
            try (PreparedStatement stmt1 = con.prepareStatement(query1);
                 PreparedStatement stmt2 = con.prepareStatement(query2)) {
                stmt1.executeUpdate();
                stmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert sample data into tables
    void insertData() {
        this.registerDriver();
        try (Connection con = DriverManager.getConnection(this.URL, this.USER, this.PASSWD)) {
            String insertRegistration = "INSERT INTO stdRegistration (stdID, stdName) VALUES (?, ?)";
            String insertDetails = "INSERT INTO stdDetails (stdID, stdAge) VALUES (?, ?)";
            try (PreparedStatement pstmt1 = con.prepareStatement(insertRegistration);
                 PreparedStatement pstmt2 = con.prepareStatement(insertDetails)) {
                pstmt1.setInt(1, 1);
                pstmt1.setString(2, "Anirudha ");
                pstmt1.executeUpdate();
                pstmt1.setInt(1, 2);
                pstmt1.setString(2, "Purva");
                pstmt1.executeUpdate();

                pstmt2.setInt(1, 1);
                pstmt2.setInt(2, 20);
                pstmt2.executeUpdate();
                pstmt2.setInt(1, 2);
                pstmt2.setInt(2, 22);
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Demonstrate JdbcRowSet
    void readDataUsingJdbcRowSet() {
        this.registerDriver();
        try (JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet()) {
            jdbcRs.setUrl(this.URL);
            jdbcRs.setUsername(this.USER);
            jdbcRs.setPassword(this.PASSWD);
            jdbcRs.setCommand("SELECT * FROM stdRegistration");
            jdbcRs.execute();

            while (jdbcRs.next()) {
                System.out.println("JdbcRowSet -> ID: " + jdbcRs.getInt("stdID") + ", Name: " + jdbcRs.getString("stdName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Demonstrate CachedRowSet
    void readDataUsingCachedRowSet() {
        this.registerDriver();
        try (CachedRowSet cachedRs = RowSetProvider.newFactory().createCachedRowSet()) {
            cachedRs.setUrl(this.URL);
            cachedRs.setUsername(this.USER);
            cachedRs.setPassword(this.PASSWD);
            cachedRs.setCommand("SELECT * FROM stdRegistration");
            cachedRs.execute();

            while (cachedRs.next()) {
                System.out.println("CachedRowSet -> ID: " + cachedRs.getInt("stdID") + ", Name: " + cachedRs.getString("stdName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // Demonstrate WebRowSet
    void readDataUsingWebRowSet() {
        this.registerDriver();
        try (WebRowSet webRs = RowSetProvider.newFactory().createWebRowSet()) {
            webRs.setUrl(this.URL);
            webRs.setUsername(this.USER);
            webRs.setPassword(this.PASSWD);
            webRs.setCommand("SELECT * FROM stdRegistration");
            webRs.execute();

            // Write to XML (just for demonstration)
            String xmlString = "";
            webRs.writeXml(System.out);

            // Reset cursor position to the beginning before processing rows
            webRs.beforeFirst();
            
            while (webRs.next()) {
                System.out.println("WebRowSet -> ID: " + webRs.getInt("stdID") + ", Name: " + webRs.getString("stdName"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

 // Demonstrate FilteredRowSet
    void readDataUsingFilteredRowSet() {
        this.registerDriver();
        try (FilteredRowSet filteredRs = RowSetProvider.newFactory().createFilteredRowSet()) {
            filteredRs.setUrl(this.URL);
            filteredRs.setUsername(this.USER);
            filteredRs.setPassword(this.PASSWD);
            filteredRs.setCommand("SELECT * FROM stdRegistration");
            filteredRs.execute();

            // Set a filter to only display records with stdID > 1
            filteredRs.setFilter(new Predicate() {
                @Override
                public boolean evaluate(RowSet rs) {
                    try {
                        return rs.getInt("stdID") > 1;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }
                }

                @Override
                public boolean evaluate(Object value, int column) throws SQLException {
                    return false; // Not used in this example
                }

                @Override
                public boolean evaluate(Object value, String columnName) throws SQLException {
                    return false; // Not used in this example
                }
            });

            while (filteredRs.next()) {
                System.out.println("FilteredRowSet -> ID: " + filteredRs.getInt("stdID") + ", Name: " + filteredRs.getString("stdName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Demonstrate JoinRowSet
    void readDataUsingJoinRowSet() {
        this.registerDriver();
        try (JoinRowSet joinRs = RowSetProvider.newFactory().createJoinRowSet();
             CachedRowSet cachedRs1 = RowSetProvider.newFactory().createCachedRowSet();
             CachedRowSet cachedRs2 = RowSetProvider.newFactory().createCachedRowSet()) {

            // Retrieve data from stdRegistration
            cachedRs1.setUrl(this.URL);
            cachedRs1.setUsername(this.USER);
            cachedRs1.setPassword(this.PASSWD);
            cachedRs1.setCommand("SELECT stdID, stdName FROM stdRegistration");
            cachedRs1.execute();

            // Retrieve data from stdDetails
            cachedRs2.setUrl(this.URL);
            cachedRs2.setUsername(this.USER);
            cachedRs2.setPassword(this.PASSWD);
            cachedRs2.setCommand("SELECT stdID, stdAge FROM stdDetails");
            cachedRs2.execute();

            // Add RowSets to JoinRowSet
            joinRs.addRowSet(cachedRs1, "stdID");
            joinRs.addRowSet(cachedRs2, "stdID");

            while (joinRs.next()) {
                System.out.println("JoinRowSet -> ID: " + joinRs.getInt("stdID") + ", Name: " + joinRs.getString("stdName") + ", Age: " + joinRs.getInt("stdAge"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to execute the demonstration
    public static void main(String[] args) {
        RowSetDemo obj = new RowSetDemo();
        obj.createTbl();  // Create the tables
        obj.insertData();  // Insert sample data
        obj.readDataUsingJdbcRowSet();  // Demonstrate JdbcRowSet
        obj.readDataUsingCachedRowSet();  // Demonstrate CachedRowSet
        obj.readDataUsingWebRowSet();  // Demonstrate WebRowSet
        obj.readDataUsingFilteredRowSet();  // Demonstrate FilteredRowSet
        obj.readDataUsingJoinRowSet();  // Demonstrate JoinRowSet
    }
}
