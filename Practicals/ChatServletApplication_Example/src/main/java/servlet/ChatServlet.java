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
