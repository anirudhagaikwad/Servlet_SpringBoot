package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/displayCookie")
public class DisplayCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Cookie Information:</h2>");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                out.println("<p>Name: " + cookie.getName() + ", Value: " + cookie.getValue() + "</p>");
            }
        } else {
            out.println("<p>No cookies found</p>");
        }

        out.println("</body></html>");
    }
}

