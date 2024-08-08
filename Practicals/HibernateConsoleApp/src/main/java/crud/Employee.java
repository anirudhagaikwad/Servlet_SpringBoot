package crud;
/*
This code creates a new `Employee` object, sets its fields, and then uses a Hibernate `Session` to save it to the database. 
Hibernate automatically translates this into an SQL `INSERT` statement.
- Class Purpose : Represents an entity in the database.
- Annotations : Define how the class and its fields map to the database.
- Fields : Represent columns in the database table.
- Getters and Setters : Provide access to the fields.
- Hibernate Usage : Hibernate uses this class to map data between the Java application and the database. 
*/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "empdetails")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "hire_date")
    private java.sql.Date hireDate;
    
    @Column(name = "salary")
    private double salary;

    // Getters and setters
    public int getEmpId() {
        return emp_id;
    }

    public void setEmpId(int emp_id) {
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

    public java.sql.Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(java.sql.Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

/*
### Purpose of the `Employee` Class
The `Employee` class is a plain old Java object (POJO) that represents an entity in your database. It maps to a table named `empdetails` in your database. This class is used by Hibernate to perform CRUD (Create, Read, Update, Delete) operations on the `empdetails` table.

### Import Statements
These import statements bring in various classes and interfaces from the Jakarta Persistence API (JPA) and Java Standard Library:

1. `jakarta.persistence.Column`: Used to specify the details of a column in a database table.
2. `jakarta.persistence.Entity`: Specifies that the class is an entity and is mapped to a database table.
3. `jakarta.persistence.GeneratedValue`: Specifies that the value of the annotated field will be generated automatically.
4. `jakarta.persistence.GenerationType`: Enumerates the different types of primary key generation strategies.
5. `jakarta.persistence.Id`: Specifies the primary key of an entity.
6. `jakarta.persistence.Table`: Specifies the table name in the database that this entity maps to.
7. `java.sql.Date`: Represents SQL date type, used for mapping `DATE` SQL type to Java.


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

### Fields and Methods
The fields in the `Employee` class represent the columns of the `empdetails` table. Each field is mapped to a corresponding column in the table.

- Fields:
  - `emp_id`: The primary key for the employee, auto-generated by the database.
  - `firstName`: The employee's first name.
  - `lastName`: The employee's last name.
  - `email`: The employee's email address.
  - `phoneNumber`: The employee's phone number.
  - `hireDate`: The date the employee was hired.
  - `salary`: The employee's salary.

- Getters and Setters: These methods allow access to the fields. They are used by Hibernate to read from and write to the database.

*/