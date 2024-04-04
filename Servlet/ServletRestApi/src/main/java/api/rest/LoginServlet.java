package api.rest;

import dao.User;
import dao.UserService;
import dao.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SECRET_KEY = "anirudhax054d0dl45673837465837465930293847568381anirudhax054d0dl45673837465837465930293847568381anirudhax054d0dl45673837465837465930293847568381anirudhax054d0dl45673837465837465930293847568381anirudhax054d0dl45673837465837465930293847568381"; // Change this to your own secret key

    private final UserService userService;

    public LoginServlet() {
        // Instantiate the UserService implementation upon servlet creation
        this.userService = new UserServiceImpl(); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Call UserService to validate
            User user = userService.validateLogin(username, password);
            if (user != null) {
                // Generate JWT token
                String token = generateToken(user);

                // Additional info with user details
                String userDetails = "Welcome, " + user.getUsername() + "! Your email is: " + user.getEmail();

                // Setting response headers
                response.setHeader("Authorization", "Bearer " + token);
                response.setHeader("User-Details", userDetails);

                // Set response status
                response.setStatus(HttpServletResponse.SC_OK);

                // Set Content-Type header
                response.setContentType("text/plain");

                // Write response message to the response body
                response.getWriter().println("Login successful"); // Add your custom message here if needed
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Invalid credentials
                response.setContentType("text/plain");
                response.getWriter().println("Invalid username or password");
            }
        } catch (SecurityException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
            response.setContentType("text/plain");
            response.getWriter().println("Authentication failed: " + e.getMessage());
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Internal server error
            response.setContentType("text/plain");
            response.getWriter().println("An error occurred: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateToken(User user) {
        // Token expiration time (1 hour)
        long expirationTime = 3600000; // 1 hour in milliseconds
        // Generate token
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
