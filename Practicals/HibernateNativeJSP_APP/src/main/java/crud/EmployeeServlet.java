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

/**
 * The `EmployeeServlet` class handles HTTP requests and manages CRUD operations
 * (Create, Read, Update, Delete) for `Employee` entities.
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Hibernate SessionFactory to manage database sessions
    private SessionFactory sessionFactory;

    /**
     * Initializes the servlet and sets up the Hibernate SessionFactory.
     */
    @Override
    public void init() throws ServletException {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Handles GET requests. Depending on the action parameter, it either lists employees,
     * shows the form to create a new employee, shows the form to edit an existing employee,
     * or deletes an employee.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // If no action is specified, list all employees
            listEmployees(request, response);
        } else {
            // Perform specific action based on the value of the "action" parameter
            switch (action) {
                case "new":
                    showNewForm(request, response); // Show form to create a new employee
                    break;
                case "edit":
                    showEditForm(request, response); // Show form to edit an existing employee
                    break;
                case "delete":
                    deleteEmployee(request, response); // Delete an employee
                    break;
            }
        }
    }

    /**
     * Handles POST requests. Based on the action parameter, it either inserts a new employee
     * or updates an existing one.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "insert"; // Default action is to insert a new employee
        }

        // Perform the insert or update action
        switch (action) {
            case "insert":
                insertEmployee(request, response); // Insert a new employee
                break;
            case "update":
                updateEmployee(request, response); // Update an existing employee
                break;
        }
    }

    /**
     * Lists all employees by fetching them from the database and forwarding the data
     * to a JSP page to be displayed.
     */
    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> listEmployee = session.createQuery("from Employee", Employee.class).list();
            request.setAttribute("listEmployee", listEmployee);
            request.getRequestDispatcher("displayEmployee.jsp").forward(request, response);
        }
    }

    /**
     * Forwards the request to the JSP page that displays the form to create a new employee.
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registerEmployee.jsp").forward(request, response);
    }

    /**
     * Forwards the request to the JSP page that displays the form to edit an existing employee.
     * It fetches the employee's details from the database and sets them as request attributes.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Session session = sessionFactory.openSession()) {
            Employee existingEmployee = session.get(Employee.class, id);
            request.setAttribute("employee", existingEmployee);
            request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
        }
    }

    /**
     * Inserts a new employee into the database using the data submitted in the form.
     */
    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a new Employee object and set its properties from the form data
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

        // Persist the new employee to the database
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(newEmployee);
            transaction.commit();
        }

        // Redirect to the list of employees after insertion
        response.sendRedirect("employee");
    }

    /**
     * Updates an existing employee's details in the database using the data submitted in the form.
     */
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("emp_id"));
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            
            // Update the employee's details with the data from the form
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

            // Merge the updated employee back into the session
            session.merge(employee);
            transaction.commit();
        }

        // Redirect to the list of employees after the update
        response.sendRedirect("employee");
    }

    /**
     * Deletes an employee from the database based on the ID provided in the request.
     */
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.remove(employee);
            transaction.commit();
        }

        // Redirect to the list of employees after deletion
        response.sendRedirect("employee");
    }

    /**
     * Closes the Hibernate SessionFactory when the servlet is destroyed.
     */
    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
