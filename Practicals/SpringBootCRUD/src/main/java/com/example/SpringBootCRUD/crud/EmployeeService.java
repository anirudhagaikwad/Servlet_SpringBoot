package com.example.SpringBootCRUD.crud;
/*
 The `EmployeeService` class encapsulates business logic, maintains separation of concerns, and provides a modular, reusable, and testable way to manage application operations.
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

	@Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Fetch all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Fetch an employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Save a new employee
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Update an existing employee
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Delete an employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

/*

1.Class Annotation:
   - `@Service`: Marks the class as a service component in the Spring application context. It's a specialization of the `@Component` annotation, and it’s used to indicate that the class holds business logic. Spring will automatically detect it during component scanning and register it as a bean.

2.Dependency Injection:
   - `@Autowired`: Injects the `EmployeeRepository` dependency into the `EmployeeService`. Spring will provide an instance of `EmployeeRepository` when creating an instance of `EmployeeService`.

3.Constructor Injection:
   - The `EmployeeService` constructor takes `EmployeeRepository` as a parameter and initializes the `employeeRepository` field. Constructor injection is preferred for mandatory dependencies as it makes the service class immutable and easier to test.

4.Service Methods:
   - getAllEmployees(): Calls `findAll()` on `employeeRepository` to fetch all employees from the database. Returns a list of `Employee` entities.
   - getEmployeeById(Long id): Uses `findById(id)` to retrieve an employee by its ID. It returns an `Optional<Employee>`, so `orElse(null)` is used to return `null` if no employee is found.
   - saveEmployee(Employee employee): Saves a new employee or updates an existing employee using `save()` method. If the `Employee` object has a valid ID, it will perform an update; otherwise, it performs an insert.
   - updateEmployee(Employee employee): Also uses `save()` to update the employee. This is redundant in your case as `save()` can handle both insert and update operations. However, it makes the intent clear.
   - deleteEmployee(Long id): Deletes the employee with the given ID using `deleteById(id)`.

*/