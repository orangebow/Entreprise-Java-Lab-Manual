<%@ page import="java.sql.*" %>
<%
    // --- AIM 1: Demonstrate 'request' implicit object ---
    // Get parameters from the form submission using the 'request' object.
    String user = request.getParameter("username");
    String email = request.getParameter("email");

    // --- AIM 2: Insert record into Oracle Database using JSP and JDBC ---
    // Database connection details - IMPORTANT: Change these to your own!
   String dbUrl = "jdbc:postgresql://localhost:5432/enterprise";
String dbUser = "anand"; // often 'postgres' by default
String dbPass = "java";
String dbDriver = "org.postgresql.Driver";
    Connection conn = null;
    String errorMessage = "";

    try {
        // 1. Load the JDBC driver
        Class.forName(dbDriver);
        
        // 2. Establish the connection
        conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        
        // 3. Create a PreparedStatement to prevent SQL injection
        String sql = "INSERT INTO students (username, email) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // 4. Set the parameters
        pstmt.setString(1, user);
        pstmt.setString(2, email);
        
        // 5. Execute the insert
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            // --- AIM 3: Demonstrate <jsp:forward> action tag ---
            // If insert is successful, forward to the success page.
%>
            <jsp:forward page="success.jsp">
                <jsp:param name="registeredUser" value="<%= user %>" />
            </jsp:forward>
<%
        }
    } catch (Exception e) {
        // Capture the error message
        errorMessage = e.getMessage();
%>
        <jsp:forward page="error.jsp">
            <jsp:param name="error" value="<%= errorMessage %>" />
        </jsp:forward>
<%
    } finally {
        // 6. Close the connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                // Ignore
            }
        }
    }
%>