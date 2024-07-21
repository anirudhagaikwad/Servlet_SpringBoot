<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<!-- JSP program to demonstrate arithmetic operations -->
<head>
    <title>Simple Calculator</title>
</head>
<body>
    <h1>Simple Calculator</h1>

    <!-- HTML Form for user input -->
    <form action="Calculator.jsp" method="post">
        <label for="num1">Number 1:</label>
        <input type="text" id="num1" name="num1" required/><br/><br/>
        
        <label for="num2">Number 2:</label>
        <input type="text" id="num2" name="num2" required/><br/><br/>
        
        <input type="submit" value="Calculate"/>
    </form>

    <% 
        // Check if form data is available
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        if (num1Str != null && num2Str != null) {
            try {
                // Convert input strings to integers
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);

                // Perform arithmetic operations
                int sum = num1 + num2;
                int difference = num1 - num2;
                int product = num1 * num2;
                double quotient = num2 != 0 ? (double) num1 / num2 : Double.NaN; // Handle division by zero
                
                // Display results
    %>
                <h2>Results:</h2>
                <p>Sum: <%= sum %></p>
                <p>Difference: <%= difference %></p>
                <p>Product: <%= product %></p>
                <p>Quotient: <%= Double.isNaN(quotient) ? "Cannot divide by zero" : quotient %></p>
    <% 
            } catch (NumberFormatException e) {
                // Handle invalid number format
    %>
                <p>Error: Please enter valid numbers.</p>
    <% 
            }
        }
    %>
</body>
</html>
