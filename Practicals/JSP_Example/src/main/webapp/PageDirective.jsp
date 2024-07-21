<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, java.io.*"
    session="true"
    buffer="8kb"
    autoFlush="true"
    isThreadSafe="true"
    info="Example JSP Page"
    errorPage="error.jsp"
    isErrorPage="false"
    contentType="text/html; charset=UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
    <title>Example JSP Page Directive</title>
</head>
<body>
    <h1>Example JSP Page Directive</h1>
    <p>This page demonstrates the use of the <%@ page %> directive with common attributes.</p>
    <p>Current Date and Time: <%= new Date() %></p>
    <p>Request User Agent: <%= request.getHeader("User-Agent") %></p>
</body>
</html>
