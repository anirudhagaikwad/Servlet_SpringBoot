<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- JSP program to request implicit object -->
<!DOCTYPE html>
<html>
<head>
    <title>JSP Request Implicit Object Example</title>
</head>
<body>
    <h1>JSP Request Implicit Object Example</h1>

    <!-- Form to collect user input -->
    <form action="requestImplicitObject.jsp" method="post">
        <label for="username">Enter your username:</label>
        <input type="text" id="username" name="username" required />
        <br/><br/>
        <label for="email">Enter your email:</label>
        <input type="text" id="email" name="email" required />
        <br/><br/>
        <input type="submit" value="Submit" />
    </form>

    <hr/>

    <!-- Display submitted data using the 'request' implicit object -->
    <%
        // Retrieve form parameters using the 'request' implicit object
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        // Display the retrieved data
        if (username != null && email != null) {
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Email: " + email + "</p>");
        } else {
            out.println("<p>No data submitted yet.</p>");
        }
    %>
</body>
</html>
