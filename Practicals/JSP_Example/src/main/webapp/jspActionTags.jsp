<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Action Tags Example</title>
</head>
<body>
    <h1>JSP Action Tags Example</h1>

    <!-- Using jsp:useBean to instantiate a bean -->
    <!-- This creates an instance of the java.util.Date class and stores it in the 'currentDate' variable -->
    <jsp:useBean id="currentDate" class="java.util.Date" scope="page" />

    <!-- Using scriptlet to display the current time in milliseconds from the 'currentDate' bean -->
    <p>Current Date and Time: <%= currentDate.getTime() %></p>

    <!-- Using jsp:include to include another JSP file -->
    <!-- The jsp:param tags should be correctly formatted -->
    <jsp:include page="jspActionTags_Include.jsp">
        <jsp:param name="message" value="This is an included JSP file." />
    </jsp:include>

    <!-- Form to demonstrate jsp:forward and jsp:param -->
    <!-- When the form is submitted, it sends data to 'jspActionTags.jsp' using the POST method -->
    <form action="jspActionTags.jsp" method="post">
        <label for="name">Enter your name:</label>
        <input type="text" id="name" name="name" required />
        <input type="submit" value="Submit" />
    </form>

    <%
        // Check if the form data is available
        String name = request.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            // Set the 'name' attribute in the request
            request.setAttribute("name", name);
            // Forward the request to 'jspActionTags_Welcome.jsp'
            request.getRequestDispatcher("jspActionTags_Welcome.jsp").forward(request, response);
        }
    %>
</body>
</html>
