package signupin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.h2.tools.Server;

import java.sql.SQLException;

@WebServlet("/h2-console/*")
public class H2ConsoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Server webServer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // Adjust webServer initialization for H2 version 2.3.230
            webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers", "-ifNotExists").start();
        } catch (SQLException e) {
            throw new ServletException("Failed to start H2 console", e);
        }
    }

    @Override
    public void destroy() {
        if (webServer != null) {
            webServer.stop();
        }
    }
}
