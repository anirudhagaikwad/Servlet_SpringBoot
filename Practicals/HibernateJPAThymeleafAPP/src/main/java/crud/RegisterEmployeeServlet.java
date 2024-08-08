package crud;

import crud.EmployeeDao;
import crud.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.thymeleaf.context.Context;

@WebServlet("/register")
public class RegisterEmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDao();
    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher("/views/registerEmployee.html").forward(request, response);
//    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Context context = new Context();
            ThymeleafConfig.renderTemplate(request, response, "registerEmployee", context);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error rendering template registerEmployee");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String hireDateStr = request.getParameter("hireDate");
        String salaryStr = request.getParameter("salary");

        try {
            Date hireDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireDateStr);
            double salary = Double.parseDouble(salaryStr);

            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setPhoneNumber(phoneNumber);
            employee.setHireDate(hireDate);
            employee.setSalary(salary);

            employeeDao.save(employee);

            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
