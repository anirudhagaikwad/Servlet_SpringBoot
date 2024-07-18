package signupin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.html");
        } else {
            response.setContentType("text/html");
            response.getWriter().write(getWelcomePage(user.getEmail()));
        }
    }

    private String getWelcomePage(String email) {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head>" +
               "<title>Welcome</title>" +
               "</head>" +
               "<body>" +
               "<h1>Welcome " + email + "</h1>" +
               "</body>" +
               "</html>";
    }
}
