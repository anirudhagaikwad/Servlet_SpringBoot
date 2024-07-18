<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Register">
    </form>
    <p style="color: red;">
        <% if (request.getAttribute("error") != null) { %>
            <%= request.getAttribute("error") %>
        <% } %>
    </p>
</body>
</html>
