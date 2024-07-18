package signupin;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter("/register")
public class EmailPasswordFilter implements Filter {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            request.setAttribute("error", "Invalid email format");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            request.setAttribute("error", "Password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

/*
Run H2 Database Server
Ensure you start the H2 database server before running your application. You can start it using the command line or by running the H2 Console:
java -cp h2*.jar org.h2.tools.Server
This will start the H2 database server, and you should be able to access the H2 Console at http://localhost:8082.
*/