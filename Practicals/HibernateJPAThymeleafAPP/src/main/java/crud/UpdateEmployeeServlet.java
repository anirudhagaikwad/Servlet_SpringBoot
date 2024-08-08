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

@WebServlet("/update")
public class UpdateEmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDao();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Employee employee = employeeDao.findById(id);
//        request.setAttribute("employee", employee);
//        request.getRequestDispatcher("/views/updateEmployee.html").forward(request, response);
//    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int empId = Integer.parseInt(idParam);
                Employee employee = employeeDao.findById(empId);
                Context context = new Context();
                context.setVariable("employee", employee);

                ThymeleafConfig.renderTemplate(request, response, "updateEmployee", context);
            } else {
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 1;//Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String hireDateStr = request.getParameter("hireDate");
        String salaryStr = request.getParameter("salary");

        try {
            Date hireDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireDateStr);
            double salary = Double.parseDouble(salaryStr);

            Employee employee = employeeDao.findById(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setPhoneNumber(phoneNumber);
            employee.setHireDate(hireDate);
            employee.setSalary(salary);

            employeeDao.update(employee);

            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

