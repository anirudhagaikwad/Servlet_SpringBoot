<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- This JSP program demonstrates how to use the request implicit object to retrieve request parameters and display them -->
<!DOCTYPE html>
<html>
<head>
    <title>Request Implicit Object Example</title>
</head>
<body>
    <h2>Request Implicit Object Example</h2>
    <form action="requestExample.jsp" method="get">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="age">Age:</label>
        <input type="text" id="age" name="age"><br>
        <input type="submit" value="Submit">
    </form>
    <%
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        if (name != null && age != null) {
    %>
        <h3>Submitted Information</h3>
        <p>Name: <%= name %></p>
        <p>Age: <%= age %></p>
    <%
        }
    %>
</body>
</html>
