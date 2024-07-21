package requestDispatcher;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
The RequestDispatcher interface in Jakarta Servlet API allows one servlet to forward a request to another resource (such as another servlet, a JSP file, or an HTML file) on the server. 
It also allows including the content of another resource in the response. 
This is useful for modularizing web applications and enabling components to work together.

Methods of RequestDispatcher Interface
forward(ServletRequest request, ServletResponse response): Forwards a request from one servlet to another resource (servlet, JSP file, or HTML file) on the server. This method takes the ServletRequest and ServletResponse objects and allows a servlet to forward a request to another resource on the server.

include(ServletRequest request, ServletResponse response): Includes the content of a resource (servlet, JSP file, or HTML file) in the response. This method allows the original servlet to include the output of another resource in its response.
 */

@WebServlet("/forwarding")
public class RequestDispatcherForwarding extends HttpServlet {
	   // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtain a RequestDispatcher object for the resource (requestDispatcherIncluding) to forward the request to
        RequestDispatcher dispatcher = req.getRequestDispatcher("requestDispatcherIncluding");

        // Use the RequestDispatcher to forward the request and response objects to Servlet2
        dispatcher.forward(req, resp);
}
}
