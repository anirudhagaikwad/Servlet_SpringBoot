package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Retrieve the session, creating one if it doesn't exist
        HttpSession session = request.getSession(true);

        // Get the session ID
        String sessionId = session.getId();
        
        // Get the session creation time
        long creationTime = session.getCreationTime();
        
        // Get the last access time
        long lastAccessTime = session.getLastAccessedTime();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Session Tracking Example</h2>");
        out.println("<p>Session ID: " + sessionId + "</p>");
        out.println("<p>Creation Time: " + new java.util.Date(creationTime) + "</p>");
        out.println("<p>Last Access Time: " + new java.util.Date(lastAccessTime) + "</p>");

        // Retrieve the username from the session, if it exists
        String username = (String) session.getAttribute("username");
        if (username == null) {
            out.println("<p>No username found in session.</p>");
        } else {
            out.println("<p>Username: " + username + "</p>");
        }

        // Provide a link to set the username
        out.println("<p><a href='setUsername'>Set Username in Session</a></p>");
        out.println("</body></html>");
    }
}

