package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
/*
 Servlet handles file upload functionality. It retrieves the file part from the request, generates a unique file name, saves the file to the server, 
 and forwards the request to a JSP page to display the uploaded file.
 */
@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet_PDF extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("pdfFile");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = filePart.getSubmittedFileName();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                Path uploadPath = Paths.get(getServletContext().getRealPath("/") + "uploads");

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(uniqueFileName);

                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }

                // Generate a URL to access the uploaded PDF file
                String pdfURL = "uploads/" + uniqueFileName;

                // Set the URL as a request attribute and forward to the JSP page
                request.setAttribute("pdfURL", pdfURL);
                request.getRequestDispatcher("pdfUploadResult.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}

/*
Import Statement : 
- jakarta.servlet.ServletException: This import allows the servlet to throw ServletException, which can be used to indicate that something went wrong with the servlet processing.
- jakarta.servlet.annotation.MultipartConfig: This annotation marks the servlet to handle multipart/form-data requests, which are typically used for file uploads.
- jakarta.servlet.annotation.WebServlet: This annotation is used to declare a servlet and map it to a URL pattern. In this case, the servlet is mapped to `/upload`.
- jakarta.servlet.http.HttpServlet: This is the base class for HTTP servlets. It provides methods to handle HTTP requests.
- jakarta.servlet.http.HttpServletRequest: This interface provides methods to handle HTTP request information.
- jakarta.servlet.http.HttpServletResponse: This interface provides methods to handle HTTP response information.
- jakarta.servlet.http.Part: This interface provides access to a part in a multipart/form-data request. A part can be a form field or a file.
- java.io.IOException: This exception is thrown when an I/O operation fails or is interrupted.
- java.io.InputStream: This class represents an input stream of bytes.
- java.nio.file.Files: This class provides static methods for file and directory manipulation.
- java.nio.file.Path: This interface represents a file system path.
- java.nio.file.Paths: This class provides static methods to obtain a Path instance.
- java.util.UUID: This class is used to generate unique identifiers, which can be used to create unique filenames.

Annotations
- @WebServlet("/upload"): This annotation declares the servlet and maps it to the URL pattern `/upload`.
- @MultipartConfig: This annotation enables the servlet to handle multipart/form-data requests.

doPost Method
- The `doPost` method is called by the server when it receives a POST request to the `/upload` URL.
- HttpServletRequest request: This parameter provides access to request information for HTTP servlets.
- HttpServletResponse response: This parameter provides access to response information for HTTP servlets.

- Part filePart = request.getPart("pdfFile"): Retrieves the file part from the request. `pdfFile` is the name of the file input field in the form.
- if (filePart != null && filePart.getSize() > 0): Checks if the file part is not null and has some content.
- String originalFileName = filePart.getSubmittedFileName(): Gets the original file name.
- String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")): Extracts the file extension from the original file name.
- String uniqueFileName = UUID.randomUUID().toString() + fileExtension: Generates a unique file name using a UUID and appends the original file extension.
- Path uploadPath = Paths.get(getServletContext().getRealPath("/") + "uploads"): Gets the absolute path to the `uploads` directory in the web application's context.
- if (!Files.exists(uploadPath)) { Files.createDirectories(uploadPath); }: Creates the `uploads` directory if it does not exist.
- Path filePath = uploadPath.resolve(uniqueFileName): Resolves the unique file name against the `uploads` directory path.
- try (InputStream input = filePart.getInputStream()) { Files.copy(input, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING); }: Opens an input stream from the file part and copies the file content to the target path, replacing any existing file with the same name.
- String pdfURL = "uploads/" + uniqueFileName: Constructs the URL to access the uploaded PDF file.
- request.setAttribute("pdfURL", pdfURL); request.getRequestDispatcher("pdfUploadResult.jsp").forward(request, response);: Sets the URL as a request attribute and forwards the request to the `pdfUploadResult.jsp` page for displaying the upload result.
- catch (Exception e) { e.printStackTrace(response.getWriter()); }: Catches any exceptions and prints the stack trace to the response writer for debugging.

*/