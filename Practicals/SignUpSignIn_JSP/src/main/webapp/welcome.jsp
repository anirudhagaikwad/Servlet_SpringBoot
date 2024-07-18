<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= request.getAttribute("email") %></h2>
    <p>Successfully logged in.</p>
</body>
</html>
