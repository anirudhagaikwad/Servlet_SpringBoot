<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Taglib Example</title>
</head>
<body>
    <h1>JSP Taglib Example with JSTL</h1>

    <!-- Using c:set to set a variable -->
    <c:set var="greeting" value="Hello, JSP with JSTL!" />

    <!-- Using c:out to display the value of the variable -->
    <p><c:out value="${greeting}" /></p>

    <!-- Using c:forEach to iterate over a collection -->
    <c:set var="numbers" value="${[1, 2, 3, 4, 5]}" />
    <ul>
        <c:forEach var="number" items="${numbers}">
            <li>Number: <c:out value="${number}" /></li>
        </c:forEach>
    </ul>

</body>
</html>
