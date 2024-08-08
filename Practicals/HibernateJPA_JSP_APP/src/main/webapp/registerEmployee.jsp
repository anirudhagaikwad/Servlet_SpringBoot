<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Employee</title>
</head>
<body>
    <h2>Register New Employee</h2>
    <form action="employee?action=insert" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" required><br>
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" required><br>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required><br>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" name="phoneNumber" id="phoneNumber"><br>
        <label for="hireDate">Hire Date:</label>
        <input type="date" name="hireDate" id="hireDate" required><br>
        <label for="salary">Salary:</label>
        <input type="text" name="salary" id="salary"><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>
