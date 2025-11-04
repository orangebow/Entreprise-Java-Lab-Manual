<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Arithmetic Operations</title>
</head>
<body>
<h1>Arithmetic Operations</h1>
<%
    int a = 20;
    int b = 5;
%>
<table border="1">
    <tr>
        <th>Operation</th>
        <th>Result</th>
    </tr>
    <tr>
        <td>Addition</td>
        <td><%= a + b %></td>
    </tr>
    <tr>
        <td>Subtraction</td>
        <td><%= a - b %></td>
    </tr>
    <tr>
        <td>Multiplication</td>
        <td><%= a * b %></td>
    </tr>
    <tr>
        <td>Division</td>
        <td><%= a / b %></td>
    </tr>
    <tr>
        <td>Modulus</td>
        <td><%= a % b %></td>
    </tr>
</table>
</body>
</html>