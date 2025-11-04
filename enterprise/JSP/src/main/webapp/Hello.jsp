<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hello JSP</title>
</head>
<body>
    <h1>Hello World from JSP!</h1>

    <%-- This is a JSP Expression. The Java code inside is executed 
         and the result is printed directly into the HTML. --%>
    <p>This page was generated on: <%= new java.util.Date() %></p>
</body>
</html>