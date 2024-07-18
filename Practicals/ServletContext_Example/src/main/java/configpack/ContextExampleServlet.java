package configpack;
/*
 * This example shows how to use the ServletContext interface to set and get attributes and initialization parameters in a servlet using Jakarta Servlet 6.0 with annotations
 */
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name = "ContextExampleServlet",
    urlPatterns = {"/contextExample"},
    initParams = {
        @WebInitParam(name = "exampleParam", value = "ExampleValue")
    }
)
public class ContextExampleServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        // Setting an attribute in the ServletContext
        ServletContext context = getServletContext();
        context.setAttribute("exampleAttribute", "This is an example attribute value");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        // Getting an attribute from the ServletContext
        String exampleAttribute = (String) context.getAttribute("exampleAttribute");

        // Getting an initialization parameter from the ServletContext
        String exampleParam = context.getInitParameter("exampleParam");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>ServletContext Example</h1>");
        out.println("<p>Example Attribute: " + exampleAttribute + "</p>");
        out.println("<p>Example Init Param: " + exampleParam + "</p>");
        out.println("</body></html>");
    }
}

/*
Annotations:

@WebServlet is used to declare the servlet, define its URL pattern, and set initialization parameters.

@WebInitParam is used within @WebServlet to set initialization parameters.
ServletContext:

context.setAttribute("exampleAttribute", "This is an example attribute value"); sets an attribute in the ServletContext.
String exampleAttribute = (String) context.getAttribute("exampleAttribute"); retrieves the attribute.
String exampleParam = context.getInitParameter("exampleParam"); retrieves an initialization parameter set in the @WebServlet annotation.

*/