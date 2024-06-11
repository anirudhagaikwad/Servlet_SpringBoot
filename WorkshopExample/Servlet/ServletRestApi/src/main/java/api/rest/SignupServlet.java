package api.rest;

import dao.User;
import dao.UserService;
import dao.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserService userService;

    public SignupServlet() {
        // Instantiate the UserService implementation upon servlet creation
        this.userService = new UserServiceImpl(); // Replace with your implementation
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract user data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Validate data (you can use annotations if implemented)
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        // Call UserService to register      
        try {
            User newUser = new User(username, password, email);
            userService.registerUser(newUser);
            
            // Return additional information in the response
            String responseData = "User registered successfully. Username: " + newUser.getUsername() + ", Email: " + newUser.getEmail();
            response.setStatus(HttpServletResponse.SC_CREATED); // Success
            response.setContentType("text/plain");
            response.getWriter().write(responseData);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Error
            // Handle exception
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
