package servlet;
/*
 example demonstrating the use of the ServletConfig class in a servlet. 
 This example includes annotations for defining the servlet and its initialization parameters.
  HTML page showing the servlet's initialization parameters (adminEmail and websiteName) retrieved from the ServletConfig object.
   */
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
    name = "ConfigServlet",
    urlPatterns = {"/config"},
    initParams = {
        @WebInitParam(name = "adminEmail", value = "admin@example.com"),
        @WebInitParam(name = "websiteName", value = "Example Website")
    }
)
public class ConfigServlet extends HttpServlet {

   	private static final long serialVersionUID = 1L;
	private String adminEmail;
    private String websiteName;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        adminEmail = config.getInitParameter("adminEmail");
        websiteName = config.getInitParameter("websiteName");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<html><body>");
        resp.getWriter().println("<h1>Servlet Configuration Example</h1>");
        resp.getWriter().println("<p>Admin Email: " + adminEmail + "</p>");
        resp.getWriter().println("<p>Website Name: " + websiteName + "</p>");
        resp.getWriter().println("</body></html>");
    }
}

/*
Annotations: The @WebServlet annotation is used to declare the servlet and its URL patterns. The @WebInitParam annotations within initParams are used to define initialization parameters.

Initialization: The init() method is overridden to retrieve the initialization parameters from the ServletConfig object.

Handling Requests: The doGet() method is overridden to handle GET requests and generate an HTML response displaying the initialization parameters.
*/