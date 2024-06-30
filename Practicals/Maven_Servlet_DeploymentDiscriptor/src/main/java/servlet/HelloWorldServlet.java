package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
    - Artifact Id: `HelloServletProject`
    - Version: `1.0-SNAPSHOT`
    - Click `Finish`.

3. Update `pom.xml`:
    - Add the necessary dependencies and plugins to your `pom.xml`:

4. Create 'WEB_INF' folder in 'webapp'
  
5. Create and Update web.xml in folder 'WEB_INF' 
    
6. Create the Servlet:
    - Right-click on the `src/main/java` folder and select `New` > `Package`. Name it `servlet`.
    - Right-click on the `servlet` package and select `New` > `Class`. Name it `HelloWorldServlet`.

7: Clean and Build the Project
Clean the Project: Right-click on the project in Eclipse, go to Maven > Update Project..., check Force Update of Snapshots/Releases, and click OK.
Rebuild the Project: Right-click on the project and select Run As > Maven clean. After that, run Maven install.

8: Redeploy the Project
Remove Existing Deployment: Remove any existing deployment of the project from the server. This can be done by going to the Servers view in Eclipse, right-clicking on the server, and selecting Add and Remove... to remove the project.
Add and Deploy the Project Again: Re-add the project to the server and deploy it.

9: Start the Server and Test
Start the Server: Start the server from the Servers view in Eclipse.
Access the Servlet: Open a web browser and navigate to http://localhost:8080/HelloServletProject/hello to see the servlet output.

*/