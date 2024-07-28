<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload and View PDF</title>
</head>
<body>
    <h1>Upload a PDF File</h1>
    <form action="upload" method="post" enctype="multipart/form-data">
        <label for="pdfFile">Select PDF File:</label>
        <input type="file" id="pdfFile" name="pdfFile" accept=".pdf" required/>
        <input type="submit" value="Upload and View"/>
    </form>
</body>
</html>
