package crud;
/*
The `App` class contains the main method to run the application and methods for performing CRUD operations on the `Employee` class.

#### Imports
- `import org.hibernate.Session`: An interface provided by Hibernate used to get a physical connection with the database.
- `import org.hibernate.Transaction`: An interface provided by Hibernate used to control transactions.
- `import java.sql.Date`: Represents SQL date.
- `import java.util.List`: A collection framework interface used to handle a collection of objects.

#### Class Definition and Main Method
- Main Method: This is the entry point of the Java application. It creates an instance of `App` and performs CRUD operations on `Employee` objects.
- Employee Creation: Creates a new `Employee` object and sets its properties.
- Save Employee: Calls `saveEmployee()` method to save the `Employee` object to the database.
- Retrieve All Employees: Calls `getAllEmployees()` method to fetch and print all `Employee` objects.
- Update Employee: Modifies the last name of the `Employee` and calls `updateEmployee()` method.
- Delete Employee: Deletes the `Employee` object by calling `deleteEmployee()` method.

#### Save Employee Method
- saveEmployee(Employee employee): Saves the provided `Employee` object to the database.
  - Transaction: Starts a transaction.
  - Session: Opens a Hibernate session.
  - session.save(employee): Saves the `Employee` object.
  - transaction.commit(): Commits the transaction.
  - Exception Handling: Rolls back the transaction in case of an exception.

#### Get All Employees Method
- getAllEmployees(): Fetches and returns a list of all `Employee` objects from the database.
  - Session: Opens a Hibernate session.
  - session.createQuery("from Employee", Employee.class).list(): Executes a query to retrieve all `Employee` objects.

#### Update Employee Method
- updateEmployee(Employee employee): Updates the provided `Employee` object in the database.
  - Transaction: Starts a transaction.
  - Session: Opens a Hibernate session.
  - session.update(employee): Updates the `Employee` object.
  - transaction.commit(): Commits the transaction.
  - Exception Handling: Rolls back the transaction in case of an exception.

#### Delete Employee Method
- deleteEmployee(int empId): Deletes the `Employee` object with the provided `empId` from the database.
  - Transaction: Starts a transaction.
  - Session: Opens a Hibernate session.
  - session.get(Employee.class, empId): Fetches the `Employee` object with the given ID.
  - session.delete(employee): Deletes the `Employee` object if it exists.
  - transaction.commit(): Commits the transaction.
  - Exception Handling: Rolls back the transaction in case of an exception.

The `App` class serves as the main driver of the application, performing CRUD operations on the `Employee` entity using Hibernate for ORM (Object-Relational Mapping). The methods for saving, retrieving, updating, and deleting `Employee` objects leverage Hibernate sessions and transactions to interact with the database. 
*/
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        employee.setLastName("Smith");
        app.updateEmployee(employee);
        
        // Delete the employee
        app.deleteEmployee(employee.getEmpId());
    }
    
    public void saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
    
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void deleteEmployee(int empId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                session.delete(employee);
                System.out.println("Employee is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

/* 
How to Run Project :
Step 1: mvn clean
Step 2: mvn install
Step 3: java -cp target/HibernateConsoleApp-1.0-SNAPSHOT.jar crud.App
----------------------------------------------------------------------
If you encounter a 'ClassNotFoundException', it might be because your .jar file is missing some classes. In this case, manually building your .jar file can help ensure all necessary classes are included.

Step 4: mvn clean package
Step 5: Check JAR Contents : jar tf target/HibernateConsoleApp-1.0-SNAPSHOT.jar
Step 6: Verify Directory Structure
Ensure that the `crud.App` class is properly compiled and packaged into the JAR file. Your JAR file should have the following structure:
HibernateConsoleApp-1.0-SNAPSHOT.jar
│
├── META-INF
│   └── MANIFEST.MF
└── crud
    ├── App.class
    ├── Employee.class
    └── HibernateUtil.class

Step 7:Check MANIFEST.MF File
Make sure the `MANIFEST.MF` file in the `META-INF` directory specifies the correct main class. It should contain:
Manifest-Version: 1.0
Main-Class: crud.App

If the `MANIFEST.MF` is missing or incorrect, the JVM won’t be able to locate the entry point for your application.

Step 8:Rebuild the Project
To ensure that everything is compiled and packaged correctly, follow these steps:
mvn clean install
mvn package
Ensure there are no errors during this process.
---------------------------------------------------
Hope it will be resolved, otherwise follow these steps:

If rebuilding through Maven fails, manually create the JAR file:
Step 9: Compile the Classes
Ensure your classes are compiled into `target/classes`:
javac -d target/classes src/main/java/crud/*.java

Step 10: Create the Manifest File
Create a file named MANIFEST.MF at the absolute path 'src/main/resources/META-INF/MANIFEST.MF'
with the following content:
Manifest-Version: 1.0
Main-Class: crud.App

Step 11: Package into JAR
Package the compiled classes into a JAR file with the manifest:
jar cmf MANIFEST.MF target/HibernateConsoleApp-1.0-SNAPSHOT.jar -C target/classes .

Step 12: Verify JAR Creation
Ensure that the newly created JAR file contains the correct class files and manifest:
jar tf target/HibernateConsoleApp-1.0-SNAPSHOT.jar

Look for `crud/App.class` and confirm it is present.

These steps should help you manually compile and package your Java application into a JAR file, ensuring all necessary components are included and the `ClassNotFoundException` issue is resolved.
*/