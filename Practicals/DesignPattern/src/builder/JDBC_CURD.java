package builder;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_CURD {
    private Connection connection;

    public JDBC_CURD(Connection connection) {
        this.connection = connection;
    }

    void createTbl() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS stdRegistration (stdID INTEGER NOT NULL, stdName VARCHAR(255))";
            Statement stmt = connection.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void insertData(int stdID, String stdName) {
        try {
            String query = "INSERT INTO stdRegistration (stdID, stdName) VALUES (?, ?)";
            try (java.sql.PreparedStatement pstmt = connection.prepareStatement(query)) {
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
            String query = "SELECT * FROM stdRegistration";
            Statement stmt = connection.createStatement();
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
            String query = "UPDATE stdRegistration SET stdName = ? WHERE stdID = ?";
            try (java.sql.PreparedStatement pstmt = connection.prepareStatement(query)) {
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
            String query = "DELETE FROM stdRegistration WHERE stdID = ?";
            try (java.sql.PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, stdID);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Build the DatabaseConnection object using the Builder pattern
        DatabaseConnection dbConnection = new DatabaseConnection.Builder()
            .setUrl("jdbc:mysql://localhost:3306/practical?autoReconnect=true&SSL=false")
            .setUser("root")
            .setPasswd("")
            .build();

        // Pass the connection to JDBC_CURD
        JDBC_CURD obj = new JDBC_CURD(dbConnection.getConnection());
        obj.createTbl();
        obj.insertData(1, "Anirudha");
        obj.insertData(2, "Dinesh");
        obj.readData();
        obj.updateData(1, "Asmita");
        obj.readData();
        obj.deleteData(2);
        obj.readData();

        // Close the connection when done
        try {
            if (dbConnection.getConnection() != null && !dbConnection.getConnection().isClosed()) {
                dbConnection.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


/*
The Builder pattern is another creational design pattern that is used to construct complex objects step by step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
In the context of a JDBC connection, the Builder pattern might not seem as natural as for other use cases (like building a complex object with many fields), but we can still implement it to illustrate how it can be applied.

#Steps to Create the Builder Pattern

##Create a Product Class:
Define the class that will be created by the builder.

##Create a Builder Class:
Define a static nested class (the Builder) within the main class or as a separate class. This class will have methods to set various parameters for the object being built.

##Add Methods to the Builder Class:
Add methods to the Builder class to set each parameter and return the Builder object itself to allow for method chaining.

##Build Method:
The Builder class should have a build() method that constructs the main object using the set parameters.

##Main Class Constructor:
The main class should have a private constructor that takes the Builder as a parameter.
Use the Builder in Your Application:

##Use the Builder to create and configure instances of the main class.
*/