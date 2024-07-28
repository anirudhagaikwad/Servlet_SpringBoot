<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ page import="jakarta.servlet.http.*, jakarta.servlet.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload and View PDF</title>
</head>
<body>
    <h1>Upload a PDF File</h1>
    <form action="uploadAndViewPDF.jsp" method="post" enctype="multipart/form-data">
        <label for="pdfFile">Select PDF File:</label>
        <input type="file" id="pdfFile" name="pdfFile" accept=".pdf" required/>
        <input type="submit" value="Upload and View"/>
    </form>

    <% 
        // Handling the file upload
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
                List<FileItem> items = upload.parseRequest(request);
                for(FileItem item : items){
                    if(!item.isFormField()){
                        String fileName = FilenameUtils.getName(item.getName());
                        File uploadedFile = new File(getServletContext().getRealPath("/") + "uploads/" + fileName);
                        uploadedFile.getParentFile().mkdirs(); // Create directories if not exist
                        item.write(uploadedFile);

                        // Generate a URL to access the uploaded PDF file
                        String pdfURL = "uploads/" + fileName;

                        // Open the PDF file in a new tab
                        out.println("<script type='text/javascript'>");
                        out.println("window.open('" + pdfURL + "', '_blank');");
                        out.println("</script>");
                    }
                }
            } catch (Exception e) {
                out.println("File upload failed: " + e.getMessage());
            }
        }
    %>
</body>
</html>
