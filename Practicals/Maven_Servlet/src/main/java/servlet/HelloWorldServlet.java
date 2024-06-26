package servlet;
/* A servlet program to print hello world */
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Print to console
        System.out.println("Hello World from Servlet!");

        // Print to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello World from Servlet!</h1>");
        out.println("</body></html>");
    }
}

/*
### Steps to Create the Project in Eclipse: ###

1. Set up your Maven project:
    - Open Eclipse.
    - Go to `File` > `New` > `Maven Project`.
    - Check `Create a simple project (skip archetype selection)`.
    - Click `Next`.

2. Configure your project:
    - Group Id: `com.example`
    - Artifact Id: `hello_world_servlet`
    - Version: `1.0-SNAPSHOT`
    - Click `Finish`.

2. Update `pom.xml`:
    - Add the necessary dependencies and plugins to your `pom.xml`:
    
3. Create the Servlet:
    - Right-click on the `src/main/java` folder and select `New` > `Package`. Name it `servlet`.
    - Right-click on the `com.example.servlet` package and select `New` > `Class`. Name it `HelloWorldServlet`.
    
4. Build the Project:
    - Right-click on the project.
    - Select `Run As` > `Maven build...`.
    - In the `Goals` field, enter `clean install` and click `Run`.

5. Deploy and Run the Servlet:
    - To deploy and run the servlet, you can use a servlet container like Apache Tomcat:
      - Download and set up Apache Tomcat.
      - In Eclipse, right-click on the project and select `Run As` > `Run on Server`.
      - Choose the Apache Tomcat server and follow the prompts to configure and start it.

6. Access the Servlet:
    - Open a web browser and go to `http://localhost:8080/hello_world_servlet/hello`.

You should see "Hello World from Servlet!" displayed on the HTML page and also printed to the console where Tomcat is running.
*/