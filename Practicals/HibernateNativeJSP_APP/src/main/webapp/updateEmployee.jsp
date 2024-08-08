<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="employee?action=update" method="post">
        <input type="hidden" name="emp_id" value="${employee.emp_id}">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" value="${employee.firstName}" required><br>
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" value="${employee.lastName}" required><br>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="${employee.email}" required><br>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" name="phoneNumber" id="phoneNumber" value="${employee.phoneNumber}"><br>
        <label for="hireDate">Hire Date:</label>
        <input type="date" name="hireDate" id="hireDate" value="${employee.hireDate}" required><br>
        <label for="salary">Salary:</label>
        <input type="text" name="salary" id="salary" value="${employee.salary}"><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
