package crud;

import crud.EmployeeDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteEmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.delete(id);
        response.sendRedirect("list");
    }
}
