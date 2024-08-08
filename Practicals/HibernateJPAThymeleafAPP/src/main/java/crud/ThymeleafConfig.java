package crud;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ThymeleafConfig {

    private static TemplateEngine templateEngine;

    static {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("views/"); // Directory in src/main/resources
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

//    public static void renderTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, Context context) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        templateEngine.process(templateName, context, response.getWriter());
//    }
    
    public static void renderTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, Context context) throws IOException {
        System.out.println("Rendering template: " + templateName);
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process(templateName, context, response.getWriter());
    }

}
