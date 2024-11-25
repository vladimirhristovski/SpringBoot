package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wpaud.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h3>User Info</h3>");
        out.format("IP Adress: %s</br>", ipAddress);
        out.format("Client Agent: %s", clientAgent);

        out.println("<h3>Category List</h3>");
        out.println("<ul>");
        categoryService.listCategories().forEach(r -> {
            out.format("<li>%s (%s)</li>", r.getName(), r.getDescription());
        });
        out.println("</ul>");

        out.println("<h3>Add Category</h3>");
        out.println("<form method = 'POST' action = '/servlet/category'>");
        out.println("<label for = 'name'>Name:</label>");
        out.println("<input id = 'name' type = 'text' name = 'name'/></br>");
        out.println("<label for = 'description'>Description:</label>");
        out.println("<input id = 'description' type = 'text' name = 'description'/></br>");
        out.println("<input type = 'submit' value = 'Submit'/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("description");
        categoryService.create(categoryName, categoryDescription);
        resp.sendRedirect("/servlet/category");
    }
}
