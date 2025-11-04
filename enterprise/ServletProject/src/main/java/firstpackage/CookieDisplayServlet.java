package firstpackage;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/display-cookie")
public class CookieDisplayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Your Cookies</h2>");

        // 1. Get all cookies from the browser's request
        Cookie[] cookies = request.getCookies();

        // 2. Check if any cookies were found
        if (cookies != null && cookies.length > 0) {
            out.println("<table border='1'><tr><th>Cookie Name</th><th>Cookie Value</th></tr>");
            // 3. Loop through the array and display each cookie's name and value
            for (Cookie cookie : cookies) {
                out.println("<tr><td>" + cookie.getName() + "</td><td>" + cookie.getValue() + "</td></tr>");
            }
            out.println("</table>");
        } else {
            out.println("<p>No cookies found.</p>");
        }
        out.println("<br><a href='create-cookie'>Create New Cookie</a>");
        out.println("</body></html>");
    }
}