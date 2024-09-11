package com.example.SpringBootCRUD.crud;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "empdetails") // This maps to the 'empdetails' table in the database
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId; // Mapped to the 'emp_id' column in the table
    
    @Column(name = "first_name", nullable = false)
    private String firstName; // Mapped to the 'first_name' column and not null
    
    @Column(name = "last_name", nullable = false)
    private String lastName; // Mapped to the 'last_name' column and not null
    
    @Column(name = "email", nullable = false, unique = true)
    private String email; // Mapped to the 'email' column, must be unique and not null
    
    @Column(name = "phone_number")
    private String phoneNumber; // Mapped to the 'phone_number' column
    
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate; // Mapped to the 'hire_date' column, not null
    
    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary; // Mapped to the 'salary' column with decimal precision
    
    /*
- Precision defines the total number of digits that can be stored in a column, including both the digits before and after the decimal point.
For example, precision = 10 means the total number of digits allowed for the column is 10.
- Scale defines the number of digits to the right of the decimal point.
For example, scale = 2 means there can be 2 digits after the decimal point.
     */

	public Employee(Long empId, String firstName, String lastName, String email, String phoneNumber, LocalDate hireDate,
			BigDecimal salary) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.salary = salary;
	}

	public Employee() {}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, empId, firstName, hireDate, lastName, phoneNumber, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && Objects.equals(empId, other.empId)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(hireDate, other.hireDate)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(salary, other.salary);
	}
    
    
    
}
