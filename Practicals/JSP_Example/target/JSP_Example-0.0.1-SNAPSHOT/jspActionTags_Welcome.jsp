<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <!-- Display a welcome message using the 'name' attribute set in 'jspActionTags.jsp' -->
    <h1>Welcome, <%= request.getAttribute("name") %>!</h1>
</body>
</html>
