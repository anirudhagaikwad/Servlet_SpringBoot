package com.example.SpringBootCRUD.crud;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display the list of employees
    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "listEmp"; // Thymeleaf template to display the employee list
    }

    // Show form to register a new employee
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "registerEmp"; // Thymeleaf template to register a new employee
    }

    // Save a new employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // Show form to update an employee
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "registerEmp"; // Reuse the same form for editing
        } else {
            return "redirect:/employees";
        }
    }

    // Update an employee
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
        // Fetch the existing employee from the database
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee != null) {
            // Update the existing employee's fields using camelCase
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.setHireDate(employee.getHireDate());
            existingEmployee.setSalary(employee.getSalary());

            // Save the updated employee
            employeeService.updateEmployee(existingEmployee);
        }
        return "redirect:/employees";
    }

    // Delete an employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}

/*
The `EmployeeController` class is a Spring MVC controller responsible for handling HTTP requests related to employee management. It interacts with the `EmployeeService` to perform CRUD operations and manages the flow between the web layer and the service layer.

1.Annotations:
   - `@Controller`: Marks this class as a Spring MVC controller, enabling it to handle HTTP requests.
   - `@RequestMapping("/employees")`: Maps HTTP requests starting with `/employees` to this controller.

2.Autowired Dependency:
   - `@Autowired` injects the `EmployeeService` into the controller, allowing it to use business logic defined in the service layer.

3.Constructor Injection:
   - `EmployeeController(EmployeeService employeeService)`: Constructor-based dependency injection ensures that the `EmployeeService` is provided to the controller.

### Methods

1.List Employees**:
   - `@GetMapping`: Handles GET requests to `/employees`.
   - Fetches a list of all employees from the service layer and adds it to the model.
   - Returns the name of the Thymeleaf template (`listEmp`) that displays the employee list.

2.Show Register Form:
   - `@GetMapping("/register")`: Handles GET requests to `/employees/register`.
   - Initializes a new `Employee` object and adds it to the model.
   - Returns the name of the Thymeleaf template (`registerEmp`) for registering a new employee.

3.Save Employee:
   - `@PostMapping("/save")`: Handles POST requests to `/employees/save`.
   - Saves a new employee using the service layer.
   - Redirects to `/employees` to display the updated list of employees.

4.Show Edit Form:
   - `@GetMapping("/edit/{id}")`: Handles GET requests to `/employees/edit/{id}`.
   - Retrieves an existing employee by ID and adds it to the model.
   - Returns the name of the Thymeleaf template (`registerEmp`) for editing the employee.

5.Update Employee:
   - `@PostMapping("/update/{id}")`: Handles POST requests to `/employees/update/{id}`.
   - Retrieves the existing employee by ID, updates its fields with new data, and saves the updated employee.
   - Redirects to `/employees` to display the updated list.

6.Delete Employee:
   - `@GetMapping("/delete/{id}")`: Handles GET requests to `/employees/delete/{id}`.
   - Deletes the employee by ID using the service layer.
   - Redirects to `/employees` to display the updated list.

### Purpose
- Separation of Concerns: The controller handles HTTP requests and responses, while the service layer manages business logic. This separation keeps the code organized and modular.
- Data Binding: `@ModelAttribute` is used to bind form data to the `Employee` object.
- Redirection: After performing operations like save, update, or delete, the controller redirects to `/employees` to refresh the employee list view.

This approach ensures that the web layer (controller) is clean and focused on handling user interactions, while the service layer deals with business logic and data manipulation. 
 
*/