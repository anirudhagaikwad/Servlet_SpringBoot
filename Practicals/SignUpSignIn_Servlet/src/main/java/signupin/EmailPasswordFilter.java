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
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            response.getWriter().write("Invalid email format");
            return;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            response.getWriter().write("Password must be at least 8 characters long, contain at least one number, one lowercase and one uppercase letter");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
