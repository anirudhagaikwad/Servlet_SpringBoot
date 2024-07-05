package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/createCookie")
public class CreateCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Create a new cookie
        Cookie cookie = new Cookie("username", "JakartaUser");
        // Set the max age of the cookie (in seconds)
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        // Add the cookie to the response
        response.addCookie(cookie);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Cookie created successfully!</h2>");
        out.println("<a href='displayCookie'>Go to Display Cookie Servlet</a>");
        out.println("</body></html>");
    }
}
