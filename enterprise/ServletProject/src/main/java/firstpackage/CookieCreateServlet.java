package firstpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-cookie")
public class CookieCreateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. Create a new cookie object
        // The first argument is the cookie's name, the second is its value.
        Cookie visitCookie = new Cookie("lastVisit", LocalDateTime.now().toString());

        // 2. Set the cookie's lifespan (in seconds)
        // This makes the cookie persistent for 30 days.
        visitCookie.setMaxAge(60 * 60 * 24 * 30);

        // 3. Add the cookie to the HTTP response
        // This is the step that sends the cookie to the browser.
        response.addCookie(visitCookie);

        // Send a confirmation message to the user
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Cookie Created</h1>");
        out.println("<p>A cookie named 'lastVisit' has been sent to your browser.</p>");
        out.println("<a href='display-cookie'>View Cookies</a>");
        out.println("</body></html>");
    }
}