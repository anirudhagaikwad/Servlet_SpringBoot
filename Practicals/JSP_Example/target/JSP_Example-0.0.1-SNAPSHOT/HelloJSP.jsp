<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<!-- JSP program to display hello world. -->
<!DOCTYPE html>
<html>
<head>
    <title>Hello JSP</title>
</head>
<body>
     <%
        String str = "Hello World JSP";
    %>
    <h1><%= str %></h1>
    <p>Current Date and Time: <%= new Date() %></p>
</body>
</html>
