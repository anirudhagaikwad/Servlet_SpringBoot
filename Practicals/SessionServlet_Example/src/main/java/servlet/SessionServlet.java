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

/*
demonstrates how to use HTTP sessions in a Jakarta Servlet application. The servlet manages session tracking and retrieves information about the session, such as its ID, creation time, last access time, and an attribute (in this case, a username) stored in the session.

# Servlet Declaration:
@WebServlet("/session"): This annotation declares the servlet and maps it to the URL pattern /session. When a client makes a request to this URL, the SessionServlet will handle the request.

#doGet Method:
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException: This method handles GET requests. When the client sends a GET request to the /session URL, this method is executed.

#Content Type:
response.setContentType("text/html");: Sets the response content type to text/html, indicating that the server's response will be an HTML document.

#Session Handling:
HttpSession session = request.getSession(true);: Retrieves the current session associated with the request. If there is no current session, this method creates a new one. The true parameter ensures a session is created if one does not exist.
String sessionId = session.getId();: Retrieves the unique session ID.
long creationTime = session.getCreationTime();: Retrieves the time when the session was created, represented as milliseconds since January 1, 1970, 00:00:00 GMT.
long lastAccessTime = session.getLastAccessedTime();: Retrieves the time when the session was last accessed, represented as milliseconds since January 1, 1970, 00:00:00 GMT.

#Response Writing:
PrintWriter out = response.getWriter();: Retrieves a PrintWriter object to send character text to the client.

The HTML response is built using the PrintWriter object:

The session ID, creation time, and last access time are displayed.
The creation and last access times are converted to readable date formats using new java.util.Date(time).

#Session Attribute Handling:
String username = (String) session.getAttribute("username");: Attempts to retrieve the "username" attribute from the session. If the attribute does not exist, it returns null.

If the username is null, a message indicating no username is found in the session is displayed. Otherwise, the username is displayed.

Link to Set Username:

out.println("<p><a href='setUsername'>Set Username in Session</a></p>");: Provides a hyperlink to another servlet or URL (setUsername) where the user can set the username in the session. The implementation of setUsername is not shown in this example, but it would typically be another servlet that allows the user to set the "username" attribute in the session.

*/
