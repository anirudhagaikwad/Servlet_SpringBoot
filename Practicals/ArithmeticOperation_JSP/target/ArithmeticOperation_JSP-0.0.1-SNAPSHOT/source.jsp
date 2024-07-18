<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- This JSP program demonstrates the use of the <jsp:forward> action tag to forward a request from one JSP page to another. -->
<!DOCTYPE html>
<html>
<head>
    <title>Source Page</title>
</head>
<body>
    <h2>Source Page</h2>
    <p>This page will forward to the destination page.</p>
    <jsp:forward page="destination.jsp"/>
</body>
</html>
