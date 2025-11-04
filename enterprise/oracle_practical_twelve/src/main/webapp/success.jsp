<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Successful</title>
</head>
<body>
    <h2>✅ Success!</h2>
    <p>User '<strong><%= request.getParameter("registeredUser") %></strong>' has been successfully registered in the database.</p>
    <a href="index.jsp">Register another user</a>
</body>
</html>

