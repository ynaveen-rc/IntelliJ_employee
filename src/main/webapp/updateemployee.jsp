<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee management system</title>
</head>
<body>
<form method="post" action="updateemployee/${employee.empid}" modelAttribute="employee">
    <input type="text" placeholder="Employee Id" name="empid" value="${employee.empid}"><br><br>
    <input type="text" placeholder="Employee Name" name="empname"><br><br>
    <input type="email" placeholder="Mail Id" name="empmail"><br><br>
    <input type="text" placeholder="Department" name="department"><br><br>
    <input type="text" placeholder="Manager Name" name="manager"><br><br>
    <input type="submit" value="Update Employee"><br><br>
</form>
</body>
</html>
