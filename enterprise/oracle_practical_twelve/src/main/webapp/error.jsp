<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Error</title>
</head>
<body>
    <h2>❌ Error!</h2>
    <p>An error occurred during registration. Please try again.</p>
    <p><strong>Details:</strong> <%= request.getParameter("error") %></p>
    <a href="index.jsp">Go back</a>
</body>
</html>