package web_page_nine;


import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutServelet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        HttpSession session = request.getSession(false); // Do not create new session
        
        if (session != null) {
            session.invalidate(); // Invalidate the existing session
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>You have been logged out</h1>");
        out.println("<a href='login.html'>Login again</a>");
        out.println("</body></html>");
    }
}
