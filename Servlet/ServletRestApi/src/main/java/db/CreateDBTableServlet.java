package db;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createTable")
public class CreateDBTableServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateDBTableServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Call the createTbl() method from CreateDatabaseTable class
            CreateDatabaseTable.createTbl();
            response.getWriter().println("Database table created successfully.");
        } catch (Exception e) {
            response.getWriter().println("Error occurred while creating database table: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handling POST requests, if needed
        doGet(request, response);
    }
}

