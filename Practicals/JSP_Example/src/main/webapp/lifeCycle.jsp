<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!--  Example JSP Page to Display Lifecycle Methods -->
<!DOCTYPE html>
<html>
<head>
    <title>JSP Lifecycle Methods</title>
</head>
<body>
    <h1>JSP Lifecycle Methods</h1>
    <p>
        <strong>jspInit() method executed:</strong> <%= isInitialized %><br/>
        <strong>_jspService() method executed:</strong> <%= requestCount %> times<br/>
    </p>
</body>
</html>

<%!
    private static boolean isInitialized = false;
    private static int requestCount = 0;

    public void jspInit() {
        isInitialized = true;
        System.out.println("jspInit() method executed");
    }

    public void jspDestroy() {
        System.out.println("jspDestroy() method executed");
    }
%>

<%
    requestCount++;
    System.out.println("_jspService() method executed " + requestCount + " times");
%>
