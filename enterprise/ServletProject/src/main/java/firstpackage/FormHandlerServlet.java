package firstpackage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This annotation maps the servlet to the URL used in the form's action
@WebServlet("/formHandler")
public class FormHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve data from the form using the 'name' attributes
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        out.println("<html><body>");
        out.println("<h2>Submitted Successfully</h2>");
        out.println("<p><strong>Name:</strong> " + username + "</p>");
        out.println("<p><strong>Email:</strong> " + email + "</p>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect to the form if someone tries to access the servlet directly
        response.sendRedirect("form.html");
    }
}