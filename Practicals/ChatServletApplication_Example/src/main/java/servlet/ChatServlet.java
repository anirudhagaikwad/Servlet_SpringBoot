package servlet;
/*
    A servlet to serve the chat page
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.sendRedirect("login.html");
            return;
        }

        response.setContentType("text/html");
        response.getWriter().write(getChatPage(username));
    }

    private String getChatPage(String username) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "<title>Chat Application</title>" +
               "<style>" +
               "#chatBox { width: 300px; height: 400px; border: 1px solid #ccc; overflow-y: scroll; padding: 10px; }" +
               "#messageInput { width: 200px; }" +
               "</style>" +
               "</head>" +
               "<body>" +
               "<h1>Welcome " + username + " to Chat Server</h1>" +
               "<div id='chatBox'></div>" +
               "<input type='text' id='messageInput' placeholder='Enter your message'/>" +
               "<button onclick='sendMessage()'>Send</button>" +
               "<script>" +
               "var ws = new WebSocket('ws://' + location.host + '/ChatServletProject/chatServer');" +
               "ws.onmessage = function(event) { var chatBox = document.getElementById('chatBox'); chatBox.innerHTML += '<p>' + event.data + '</p>'; chatBox.scrollTop = chatBox.scrollHeight; };" +
               "function sendMessage() { var input = document.getElementById('messageInput'); ws.send(input.value); input.value = ''; }" +
               "</script>" +
               "</body>" +
               "</html>";
    }
}

/*
This `ChatServlet` Java class serves the chat page to users. It is a servlet that handles HTTP requests and responses to deliver a simple chat application interface.

### Imports and Their Usage

1. `jakarta.servlet.ServletException`: Exception thrown to indicate a servlet problem.
2. `jakarta.servlet.annotation.WebServlet`: Annotation to declare a servlet and map it to a URL pattern.
3. `jakarta.servlet.http.HttpServlet`: Provides methods to handle HTTP requests and responses.
4. `jakarta.servlet.http.HttpServletRequest`: Represents an HTTP request.
5. `jakarta.servlet.http.HttpServletResponse`: Represents an HTTP response.
6. `jakarta.servlet.http.HttpSession`: Provides a way to identify a user across multiple requests and store information about that user.

### Class and Methods Explanation

1. `@WebServlet("/chat")`
   - Declares the servlet and maps it to the URL pattern `/chat`.
   - This means the servlet will handle requests sent to the `/chat` URL.

2. `protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`
   - Handles GET requests to the `/chat` URL.
   - Retrieves the `HttpSession` from the request to check if a username is stored.

3. `HttpSession session = request.getSession();`
   - Retrieves the current HTTP session, or creates one if it doesn't exist.

4. `String username = (String) session.getAttribute("username");`
   - Retrieves the `username` attribute from the session.

5. `if (username == null) { response.sendRedirect("login.html"); return; }`
   - If the `username` is not found in the session, the user is redirected to the `login.html` page.

6. `response.setContentType("text/html");`
   - Sets the response content type to HTML.

7. `response.getWriter().write(getChatPage(username));`
   - Writes the chat page HTML to the response.

8. `private String getChatPage(String username)`
   - Generates the HTML content of the chat page.
   - The `username` is included in the welcome message.

### HTML and JavaScript Explanation

- HTML Structure:
  - The HTML structure includes a chat box, an input field for messages, and a send button.
  - CSS styles are included to define the chat box and input field appearance.

- JavaScript:
  - A WebSocket connection is established with the server at the URL `/ChatServletProject/chatServer`.
  - The `ws.onmessage` function is defined to handle incoming messages and display them in the chat box.
  - The `sendMessage` function sends the message typed in the input field to the server and clears the input field.

### Key Points

- Session Management: The servlet uses HTTP sessions to manage user login status. If a user is not logged in (i.e., `username` is not in the session), they are redirected to a login page.
- Dynamic HTML Generation: The `getChatPage` method generates the HTML for the chat page, incorporating the user's username into the welcome message.
- WebSocket Integration: JavaScript is used to establish a WebSocket connection to the server, enabling real-time messaging functionality.
- Chat UI: The chat interface is built with HTML and styled using basic CSS. JavaScript handles message sending and receiving.

This servlet thus serves as the entry point for users to access the chat application, ensuring they are logged in and providing the necessary HTML and JavaScript to interact with the WebSocket chat server.
*/