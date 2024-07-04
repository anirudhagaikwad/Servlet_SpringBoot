package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Servlet initialized");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service method called");
        // Ensure to call super.service to handle subsequent requests
        super.service(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method called");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Servlet Lifecycle Example : Output printed on console </h1>");
        out.println("<p>doGet method called.</p>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed");
        super.destroy();
    }
}

