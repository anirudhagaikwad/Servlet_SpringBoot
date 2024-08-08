package signupin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        logger.info("Received registration request for email: " + email);

        if (userDAO.getUserByEmail(email) != null) {
            logger.warning("Email already registered: " + email);
            response.sendRedirect("register.html?error=Email+already+registered");
        } else {
            userDAO.addUser(email, password);
            logger.info("User registered successfully: " + email);
            response.sendRedirect("login.html");
        }
    }
}
