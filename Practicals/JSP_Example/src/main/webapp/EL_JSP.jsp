<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Expression Language Example</title>
</head>
<body>
    <h1>Expression Language (EL) Example</h1>
    
    <%-- Setting request parameters and attributes --%>
    <%
        request.setAttribute("username", "JakartaUser");
        request.setAttribute("age", 25);
        
        User user = new User();
        user.setName("Anirudha");
        user.setAge(38);
        request.setAttribute("user", user);
        
        List<String> colors = Arrays.asList("Red", "Green", "Blue");
        request.setAttribute("colors", colors);
    %>
    
    <!-- Accessing request parameters using EL -->
    <p>Username: ${param.username}</p>
    
    <!-- Accessing attributes using EL -->
    <p>Username: ${username}</p>
    <p>Age: ${age}</p>
    
    <!-- Accessing JavaBean properties using EL -->
    <p>User Name: ${user.name}</p>
    <p>User Age: ${user.age}</p>
    
    <!-- Accessing list elements using EL -->
    <p>First Color: ${colors[0]}</p>
    
    <!-- Using EL operators -->
    <p>Sum of 10 and 20: ${10 + 20}</p>
    <p>Is 10 greater than 5? ${10 > 5}</p>
    <p>Logical AND (true && false): ${true && false}</p>
    <p>Conditional Operator (3 > 2 ? 'Yes' : 'No'): ${3 > 2 ? 'Yes' : 'No'}</p>
    
</body>
</html>
