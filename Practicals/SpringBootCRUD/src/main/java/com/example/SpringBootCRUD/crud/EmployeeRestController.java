package com.example.SpringBootCRUD.crud;

/*
 * Here in this file change the @Controller annotation to @RestController, and modify the return values to return JSON
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public List<Employee> listEmployees() {
        return employeeService.getAllEmployees();
    }

    // Register a new employee
    @PostMapping("/register")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

// Get a specific employee by ID   
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            System.out.println("Employee: " + employee);
            return employee;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    }


    // Update an employee
    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.setHireDate(employee.getHireDate());
            existingEmployee.setSalary(employee.getSalary());

            employeeService.updateEmployee(existingEmployee);
            return existingEmployee;
        }
        return null;
    }

    // Delete an employee
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully!";
    }
}


/*
#### Key Changes:
-> @RestController: Replaced @Controller with @RestController to return JSON responses.
-> JSON Binding: Used @RequestBody in POST and PUT methods to bind JSON data from the request.
-> API Endpoints: Updated URLs (e.g., /api/employees) to make API more RESTful.
-> Responses: Methods return objects (e.g., Employee) or messages (for delete), which Postman will display as JSON.

---------------------------------------------------------
#### To test your Spring Boot REST controller using Postman, follow these steps:

### 1.Start Your Spring Boot Application
   Ensure your Spring Boot application is running. If you're using the default settings, it will run on `http://localhost:8080`.

### 2.Testing the REST Endpoints with Postman
----------------------------------------------------------
#### Testing with Postman:
-> GET: Use GET http://localhost:8080/api/employees to get a list of all employees.
-> POST: Use POST http://localhost:8080/api/employees/register with JSON in the body to register a new employee. 
Example json
{
  "firstName": "Anirudha",
  "lastName": "Gaikwad",
  "email": "anirudha.gaikwad@example.com",
  "phoneNumber": "1234567890",
  "hireDate": "2023-01-01",
  "salary": 50000
}
-> GET by ID: Use GET http://localhost:8080/api/employees/{id} to fetch a specific employee by ID.
-> PUT: Use PUT http://localhost:8080/api/employees/update/{id} with JSON in the body to update an employee.
-> DELETE: Use DELETE http://localhost:8080/api/employees/delete/{id} to delete an employee by ID.

#### attached output postman screenshot
https://github.com/anirudhagaikwad/Servlet_SpringBoot/tree/master/Practicals/SpringBootCRUD/Output_POSTMAN/


*/