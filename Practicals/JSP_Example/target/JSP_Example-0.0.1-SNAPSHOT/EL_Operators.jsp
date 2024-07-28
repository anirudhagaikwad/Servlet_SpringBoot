<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.ee/taglibs/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>EL Operators Example</title>
</head>
<body>
    <h1>Expression Language (EL) Operators Example</h1>

    <%
        // Setting attributes to be used in EL
        request.setAttribute("num1", 10);
        request.setAttribute("num2", 5);
        request.setAttribute("bool1", true);
        request.setAttribute("bool2", false);
    %>

    <!-- Arithmetic Operators -->
    <h2>Arithmetic Operators</h2>
    <p>10 + 5 = ${num1 + num2}</p>
    <p>10 - 5 = ${num1 - num2}</p>
    <p>10 * 5 = ${num1 * num2}</p>
    <p>10 / 5 = ${num1 / num2}</p>
    <p>10 % 5 = ${num1 % num2}</p>

    <!-- Relational Operators -->
    <h2>Relational Operators</h2>
    <p>10 == 5: ${num1 == num2}</p>
    <p>10 != 5: ${num1 != num2}</p>
    <p>10 < 5: ${num1 < num2}</p>
    <p>10 > 5: ${num1 > num2}</p>
    <p>10 <= 5: ${num1 <= num2}</p>
    <p>10 >= 5: ${num1 >= num2}</p>

    <!-- Logical Operators -->
    <h2>Logical Operators</h2>
    <p>true && false: ${bool1 && bool2}</p>
    <p>true || false: ${bool1 || bool2}</p>
    <p>!true: ${!bool1}</p>
    <p>!false: ${!bool2}</p>

    <!-- Conditional Operator -->
    <h2>Conditional Operator</h2>
    <p>10 > 5 ? 'Yes' : 'No': ${num1 > num2 ? 'Yes' : 'No'}</p>

</body>
</html>
