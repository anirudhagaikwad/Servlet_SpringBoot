package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        response.sendRedirect("chat");
    }
}

/*
This `LoginServlet` Java class handles the login functionality for the chat application. It processes login requests, stores the username in the session, and then redirects the user to the chat page.

### Imports and Their Usage

1. `jakarta.servlet.ServletException`: Exception thrown to indicate a servlet problem.
2. `jakarta.servlet.annotation.WebServlet`: Annotation to declare a servlet and map it to a URL pattern.
3. `jakarta.servlet.http.HttpServlet`: Provides methods to handle HTTP requests and responses.
4. `jakarta.servlet.http.HttpServletRequest`: Represents an HTTP request.
5. `jakarta.servlet.http.HttpServletResponse`: Represents an HTTP response.
6. `jakarta.servlet.http.HttpSession`: Provides a way to identify a user across multiple requests and store information about that user.

### Class and Method Explanation

1. `@WebServlet("/login")`
   - Declares the servlet and maps it to the URL pattern `/login`.
   - This means the servlet will handle requests sent to the `/login` URL.

2. `protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`
   - Handles POST requests to the `/login` URL.
   - This method is used when the login form is submitted.

3. `String username = request.getParameter("username");`
   - Retrieves the `username` parameter from the HTTP request.
   - This parameter is expected to be sent from a login form where the user enters their username.

4. `HttpSession session = request.getSession();`
   - Retrieves the current HTTP session, or creates one if it doesn't exist.
   - This session will be used to store the username.

5. `session.setAttribute("username", username);`
   - Stores the `username` in the session as an attribute.
   - This makes the username available for subsequent requests, allowing the user to be identified across multiple interactions with the application.

6. `response.sendRedirect("chat");`
   - Redirects the user to the `/chat` URL after the username has been stored in the session.
   - This sends the user to the `ChatServlet`, which will check the session for the username and serve the chat page.

### Key Points

- Form Handling: This servlet is designed to handle form submissions from a login page. It expects a `POST` request containing a `username` parameter.
- Session Management: The servlet uses HTTP sessions to store the username, allowing the user to be recognized across different requests. This is essential for maintaining user state in web applications.
- Redirection: After successfully storing the username in the session, the user is redirected to the chat page, providing a seamless transition from the login page to the chat interface.

### How It Fits Into the Chat Application

1. Login Form Submission: When the user submits the login form with their username, the form data is sent as a `POST` request to the `/login` URL.
2. Username Storage: The `LoginServlet` processes this request, retrieves the username, and stores it in the session.
3. Redirection to Chat: After storing the username, the servlet redirects the user to the `/chat` URL, which is handled by the `ChatServlet`.
4. Access Control: The `ChatServlet` checks if the username is present in the session. If not, the user is redirected to the login page, ensuring that only logged-in users can access the chat interface.

Overall, the `LoginServlet` ensures that users are properly authenticated and their usernames are stored in the session, allowing them to access the chat application. 
  
*/