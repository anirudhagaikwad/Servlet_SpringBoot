package crud;


import crud.EmployeeDao;
import crud.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.thymeleaf.context.Context;

@WebServlet("/list")
public class DisplayEmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDao();
    private static final Logger LOGGER = Logger.getLogger(DisplayEmployeeServlet.class.getName());
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    	List<Employee> employees = employeeDao.findAll();
        LOGGER.info("Number of employees fetched: " + employees.size());
//        request.setAttribute("employees", employees);
//        request.getRequestDispatcher("/views/displayEmployee.html").forward(request, response);
        Context context = new Context();
        context.setVariable("employees", employees);
        ThymeleafConfig.renderTemplate(request, response, "displayEmployee", context);
        }
        catch (Exception e) {
            e.printStackTrace(); // Log the error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error rendering employee list");
        }
    }
      }