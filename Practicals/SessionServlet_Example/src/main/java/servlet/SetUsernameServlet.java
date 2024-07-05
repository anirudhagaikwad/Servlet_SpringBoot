package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setUsername")
public class SetUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve the session, creating one if it doesn't exist
        HttpSession session = request.getSession(true);

        // Set a username attribute in the session
        session.setAttribute("username", "JakartaUser");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Username set in session!</h2>");
        out.println("<p><a href='session'>Go to Session Servlet</a></p>");
        out.println("</body></html>");
    }
}

