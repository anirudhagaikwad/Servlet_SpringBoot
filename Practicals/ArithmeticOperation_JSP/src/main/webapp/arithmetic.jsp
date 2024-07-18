<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- This JSP program demonstrates how to perform basic arithmetic operations. -->
<!DOCTYPE html>
<html>
<head>
    <title>Arithmetic Operations</title>
</head>
<body>
    <h2>Arithmetic Operations</h2>
    <%
        int num1 = 10;
        int num2 = 5;
        int sum = num1 + num2;
        int difference = num1 - num2;
        int product = num1 * num2;
        int quotient = num1 / num2;
        int remainder = num1 % num2;
    %>
    <p>Number 1: <%= num1 %></p>
    <p>Number 2: <%= num2 %></p>
    <p>Sum: <%= sum %></p>
    <p>Difference: <%= difference %></p>
    <p>Product: <%= product %></p>
    <p>Quotient: <%= quotient %></p>
    <p>Remainder: <%= remainder %></p>
</body>
</html>
