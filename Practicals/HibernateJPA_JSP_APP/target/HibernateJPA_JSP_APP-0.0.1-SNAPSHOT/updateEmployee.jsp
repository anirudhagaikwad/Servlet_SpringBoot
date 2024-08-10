<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
    <h2>Update Employee</h2>
    <form action="employee?action=update" method="post">
        <input type="hidden" name="emp_id" value="${employee.emp_id}">

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="${employee.firstName}" required><br><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="${employee.lastName}" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${employee.email}" required><br><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" value="${employee.phoneNumber}"><br><br>

        <label for="hireDate">Hire Date (yyyy-mm-dd):</label>
        <input type="date" id="hireDate" name="hireDate" value="${employee.hireDate}" required><br><br>

        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary" step="0.01" value="${employee.salary}" required><br><br>

        <input type="submit" value="Update">
    </form>
</body>
</html>
