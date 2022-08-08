<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee management system</title>
</head>
<body>
<form method="put" action="/updateemployee?empid=${employee.empid}" modelAttribute="employee">
    <input type="hidden" placeholder="Employee Id" name="empid" value="${employee.empid}">
    <input type="text" placeholder="Employee Name" name="empname"><br><br>
    <input type="email" placeholder="Mail Id" name="empmail"><br><br>
    <input type="text" placeholder="Department" name="department"><br><br>
    <input type="hidden" placeholder="Manager Name" name="manager" value="${employee.manager}">
    <input type="submit" value="Update Employee"><br><br>
</form>
</body>
</html>
