package crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.sql.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {
        App app = new App();

        // Create a new employee
        Employee employee = new Employee();
        employee.setFirstName("Anirudha");
        employee.setLastName("Gaikwad");
        employee.setEmail("ani@example.com");
        employee.setPhoneNumber("1234567890");
        employee.setHireDate(Date.valueOf("2023-01-01"));
        employee.setSalary(50000.00);

        // Save the employee
        app.saveEmployee(employee);

        // Retrieve all employees
        List<Employee> employees = app.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp.getFirstName() + " " + emp.getLastName());
        }

        // Update the employee
        employee.setLastName("Gaykwad");
        app.updateEmployee(employee);

        // Retrieve all employees again to verify the update
        employees = app.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp.getFirstName() + " " + emp.getLastName());
        }

        // Delete the employee
        app.deleteEmployee(employee.getEmpId());

        // Retrieve all employees again to verify the deletion
        employees = app.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp.getFirstName() + " " + emp.getLastName());
        }

        // Shutdown JPAUtil
        JPAUtil.shutdown();
    }

    public void saveEmployee(Employee employee) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(employee);
            transaction.commit();
            System.out.println("Employee saved successfully.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        try (EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return entityManager.createQuery("from Employee", Employee.class).getResultList();
        }
    }

    public void updateEmployee(Employee employee) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(employee);
            transaction.commit();
            System.out.println("Employee updated successfully.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int empId) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Employee employee = entityManager.find(Employee.class, empId);
            if (employee != null) {
                entityManager.remove(employee);
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

/*

Run Using : mvn exec:java -Dexec.mainClass="crud.App"

OR

java -jar target/HibernateJPAConsoleAPP-0.0.1-SNAPSHOT.jar

If Error
Re-run Maven using the -X switch to enable full debug logging.
mvn clean install -X

*/