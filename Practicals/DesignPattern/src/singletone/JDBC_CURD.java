package singletone;

/* Practical: Update the JDBC_CURD class to use the Singleton Connection object */

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_CURD {

    void createTbl() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "CREATE TABLE IF NOT EXISTS stdRegistration (stdID INTEGER NOT NULL, stdName VARCHAR(255))";
            Statement stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertData(int stdID, String stdName) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "INSERT INTO stdRegistration (stdID, stdName) VALUES (?, ?)";
            try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, stdID);
                pstmt.setString(2, stdName);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void readData() {
        try {
            Connection con = DatabaseConnection.getConnection();
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
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "UPDATE stdRegistration SET stdName = ? WHERE stdID = ?";
            try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, newStdName);
                pstmt.setInt(2, stdID);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteData(int stdID) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "DELETE FROM stdRegistration WHERE stdID = ?";
            try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, stdID);
                pstmt.executeUpdate();
            }
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

        // Close the connection when done
        DatabaseConnection.closeConnection();
    }
}
