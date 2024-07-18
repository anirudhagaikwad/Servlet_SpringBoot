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

/*
This servlet, named SetUsernameServlet, sets a "username" attribute in the session. It is intended to be used in conjunction with the SessionServlet from your previous code example. Here’s a detailed explanation of the SetUsernameServlet:

#Servlet Declaration:
@WebServlet("/setUsername"): This annotation declares the servlet and maps it to the URL pattern /setUsername. When a client makes a request to this URL, the SetUsernameServlet will handle the request.

#doGet Method:
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException: This method handles GET requests. When the client sends a GET request to the /setUsername URL, this method is executed.

#Content Type:
response.setContentType("text/html");: Sets the response content type to text/html, indicating that the server's response will be an HTML document.

#Session Handling:
HttpSession session = request.getSession(true);: Retrieves the current session associated with the request. If there is no current session, this method creates a new one. The true parameter ensures a session is created if one does not exist.

#Setting a Session Attribute:
session.setAttribute("username", "JakartaUser");: Sets a session attribute named "username" with the value "JakartaUser". This stores the username in the session.

#Response Writing:
PrintWriter out = response.getWriter();: Retrieves a PrintWriter object to send character text to the client.

The HTML response is built using the PrintWriter object:

out.println("<html><body>");: Starts the HTML document.
out.println("<h2>Username set in session!</h2>");: Displays a message indicating that the username has been set in the session.
out.println("<p><a href='session'>Go to Session Servlet</a></p>");: Provides a hyperlink to the SessionServlet, allowing the user to go back and see the session information, including the newly set username.
out.println("</body></html>");: Closes the HTML document.
*/