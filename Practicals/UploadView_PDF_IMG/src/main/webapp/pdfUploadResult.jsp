<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload Result</title>
</head>
<body>
    <h1>Upload Result</h1>
    <%
        String pdfURL = (String) request.getAttribute("pdfURL");
        if (pdfURL != null) {
    %>
        <p>File uploaded successfully. <a href="<%= pdfURL %>" target="_blank">View PDF</a></p>
    <%
        } else {
    %>
        <p>File upload failed.</p>
    <%
        }
    %>
</body>
</html>
