<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%! 
    // Declaration of a method
    private int square(int number) {
        return number * number;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Scriptlets, Declarations, and Expressions Example</title>
</head>
<body>
    <h1>JSP Scriptlets, Declarations, and Expressions Example</h1>

    <% 
        // Scriptlet to initialize variables
        String name = "Jakarta JSP";
        int number = 10;
    %>

    <p>Hello, <%= name %>!</p>
    <p>The number is: <%= number %></p>

    <!-- Using declaration method -->
    <p>The square of <%= number %> is: <%= square(number) %></p>

    <!-- Using expression -->
    <p>Current Date and Time: <%= new java.util.Date() %></p>
    <p>Result of 5 + 3: <%= 5 + 3 %></p>
</body>
</html>
