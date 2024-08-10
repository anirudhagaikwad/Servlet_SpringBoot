<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
</head>
<body>
    <h2>Employee List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Hire Date</th>
                <th>Salary</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${listEmployee}">
                <tr>
                    <td>${employee.emp_id}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.hireDate}</td>
                    <td>${employee.salary}</td>
                    <td>
                        <a href="employee?action=edit&id=${employee.emp_id}">Edit</a>
                        <a href="employee?action=delete&id=${employee.emp_id}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <a href="registerEmployee.jsp">Add New Employee</a>
</body>
</html>
