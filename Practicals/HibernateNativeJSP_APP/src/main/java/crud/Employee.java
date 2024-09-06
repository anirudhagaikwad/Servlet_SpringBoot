package crud;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The `Employee` class represents an entity that maps to the `empdetails` table
 * in the database. This class uses JPA annotations to define the mapping between
 * the class and the database table.
 */
@Entity
@Table(name = "empdetails")
public class Employee {

    // The primary key field that uniquely identifies each employee record
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private int emp_id;

    // Column mappings for the various fields in the `empdetails` table

    @Column(name = "first_name")
    private String firstName; // The first name of the employee

    @Column(name = "last_name")
    private String lastName; // The last name of the employee

    @Column(name = "email")
    private String email; // The email address of the employee

    @Column(name = "phone_number")
    private String phoneNumber; // The phone number of the employee

    @Column(name = "hire_date")
    private Date hireDate; // The date the employee was hired

    @Column(name = "salary")
    private BigDecimal salary; // The salary of the employee

    /**
     * Default constructor for the `Employee` class.
     * Required by Hibernate.
     */
    public Employee() {
        // Default constructor
    }

    /**
     * Parameterized constructor to create a new `Employee` object with specific details.
     *
     * @param firstName   The first name of the employee
     * @param lastName    The last name of the employee
     * @param email       The email address of the employee
     * @param phoneNumber The phone number of the employee
     * @param hireDate    The date the employee was hired
     * @param salary      The salary of the employee
     */
    public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    // Getter and Setter methods for accessing and modifying the employee's details

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

/*
### Import Statements
These import statements bring in various classes and interfaces from the Jakarta Persistence API (JPA) and Java Standard Library:

1. `jakarta.persistence.Column`: Used to specify the details of a column in a database table.
2. `jakarta.persistence.Entity`: Specifies that the class is an entity and is mapped to a database table.
3. `jakarta.persistence.GeneratedValue`: Specifies that the value of the annotated field will be generated automatically.
4. `jakarta.persistence.GenerationType`: Enumerates the different types of primary key generation strategies.
5. `jakarta.persistence.Id`: Specifies the primary key of an entity.
6. `jakarta.persistence.Table`: Specifies the table name in the database that this entity maps to.
7. `java.sql.Date`: Represents SQL date type, used for mapping `DATE` SQL type to Java.
8. import java.math.BigDecimal : BigDecimal is a class in the java.math package.
   Purpose: It is used for high-precision arithmetic operations on decimal numbers, which is particularly useful for financial and monetary calculations where precision is crucial.
9. import java.util.Date;: Date is a class in the java.util package.
   Purpose: It represents a specific instant in time, with millisecond precision. It is commonly used for handling dates and times in Java applications.


### Annotations and Arguments
1. `@Entity`
   - Purpose: Indicates that this class is an entity and is mapped to a database table.
   - Argument: None.

2. `@Table(name = "empdetails")`
   - Purpose: Specifies the name of the database table that this entity maps to.
   - Argument: `name = "empdetails"` specifies the name of the table in the database.

3. `@Id`
   - Purpose: Specifies the primary key of the entity.
   - Argument: None.

4. `@GeneratedValue(strategy = GenerationType.IDENTITY)`
   - Purpose: Indicates that the value of the primary key will be generated automatically.
   - Argument: `strategy = GenerationType.IDENTITY` specifies that the primary key should be generated by the database, typically using an auto-increment column.

5. `@Column(name = "first_name")`
   - Purpose: Maps the field to a specific column in the database table.
   - Argument: `name = "first_name"` specifies the column name in the table.

*/