package crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            listEmployees(request, response);
        } else {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "insert";
        }

        switch (action) {
            case "insert":
                insertEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
        }
    }

    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> listEmployee = session.createQuery("from Employee", Employee.class).list();
            request.setAttribute("listEmployee", listEmployee);
            request.getRequestDispatcher("displayEmployee.jsp").forward(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registerEmployee.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Session session = sessionFactory.openSession()) {
            Employee existingEmployee = session.get(Employee.class, id);
            request.setAttribute("employee", existingEmployee);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
        }
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(request.getParameter("firstName"));
        newEmployee.setLastName(request.getParameter("lastName"));
        newEmployee.setEmail(request.getParameter("email"));
        newEmployee.setPhoneNumber(request.getParameter("phoneNumber"));
        try {
            newEmployee.setHireDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hireDate")));
        } catch (ParseException e) {
            throw new ServletException(e);
        }
        newEmployee.setSalary(new BigDecimal(request.getParameter("salary")));

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(newEmployee);
            transaction.commit();
        }

        response.sendRedirect("employee");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("emp_id"));
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setEmail(request.getParameter("email"));
            employee.setPhoneNumber(request.getParameter("phoneNumber"));
            try {
                employee.setHireDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hireDate")));
            } catch (ParseException e) {
                throw new ServletException(e);
            }
            employee.setSalary(new BigDecimal(request.getParameter("salary")));
            session.merge(employee);
            transaction.commit();
        }

        response.sendRedirect("employee");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.remove(employee);
            transaction.commit();
        }

        response.sendRedirect("employee");
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
