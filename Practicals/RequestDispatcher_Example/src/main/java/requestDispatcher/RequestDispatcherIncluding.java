package requestDispatcher;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Define a servlet with URL pattern "/requestDispatcherIncluding"
@WebServlet("/requestDispatcherIncluding")
public class RequestDispatcherIncluding extends HttpServlet {
    // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response to "text/html"
        resp.setContentType("text/html");

        // Write a message to the response
        resp.getWriter().println("<h1>This is RequestDispatcherIncluding Servlet </h1>");

        // Obtain a RequestDispatcher object for the resource (includeServlet) to include content from
        RequestDispatcher dispatcher = req.getRequestDispatcher("includeServlet");

        // Use the RequestDispatcher to include the response from IncludedServlet
        dispatcher.include(req, resp);
    }
}
