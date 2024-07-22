<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
    <!-- Display the message passed as a parameter from 'jspActionTags.jsp' -->
    <p><strong>Included JSP File:</strong> <%= request.getParameter("message") %></p>
</body>
</html>
