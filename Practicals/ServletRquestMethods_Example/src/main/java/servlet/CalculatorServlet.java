package servlet;
/*
example demonstrating how to handle various HTTP request methods 
(doGet, doPost, doPut, doDelete, doHead, doOptions, and doTrace) 
in a single servlet. This servlet will perform basic arithmetic operations
based on the request type and parameters.
  
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {

 	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "DELETE");
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "HEAD");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "OPTIONS");
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp, "TRACE");
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp, String method) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String num1Str = req.getParameter("num1");
        String num2Str = req.getParameter("num2");
        String operation = req.getParameter("operation");

        double num1 = 0;
        double num2 = 0;
        double result = 0;
        boolean validInput = true;
        
        try {
            num1 = Double.parseDouble(num1Str);
            num2 = Double.parseDouble(num2Str);
        } catch (NumberFormatException e) {
            validInput = false;
        }

        if (validInput && num1Str != null && num2Str != null && operation != null) {
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        out.println("<html><body>");
                        out.println("<h1>Arithmetic Calculator (" + method + ")</h1>");
                        out.println("<p>Division by zero is not allowed.</p>");
                        out.println("</body></html>");
                        return;
                    }
                    break;
            }
            out.println("<html><body>");
            out.println("<h1>Arithmetic Calculator (" + method + ")</h1>");
            out.println("<p>Result: " + result + "</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Arithmetic Calculator (" + method + ")</h1>");
            out.println("<p>Invalid input. Please enter valid numbers.</p>");
            out.println("</body></html>");
        }
    }
}

/*
Fill in the form with the numbers and select an operation, then click "Calculate (POST)" or "Calculate (GET)" to see the result.
For other request methods (PUT, DELETE, HEAD, OPTIONS, TRACE), use appropriate tools (e.g., Postman, cURL) to send the requests.

Testing with cURL (Alternative)
If you prefer using the command line, you can use cURL to send these requests:

1)PUT Request:
curl -X PUT http://localhost:8080/my-web-app/calculate -d "num1=10&num2=5&operation=add"

2)DELETE Request:
curl -X DELETE http://localhost:8080/my-web-app/calculate -d "num1=10&num2=5&operation=subtract"

3)HEAD Request:
curl -I http://localhost:8080/my-web-app/calculate

4)OPTIONS Request:
curl -X OPTIONS http://localhost:8080/my-web-app/calculate

5)TRACE Request:
curl -X TRACE http://localhost:8080/my-web-app/calculate

*/