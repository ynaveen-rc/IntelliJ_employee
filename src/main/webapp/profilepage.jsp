<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee management system</title>
</head>
<body>
<h1>Welcome</h1>
<h2>${users.username}</h2>
<div align="center">
    <h1>Employee Details</h1>
    <a href="/newemployeeform">Add employee</a>
    <table border="1">
        <tr>
            <th>Employee Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Manager</th>
        </tr>
        <c:forEach var="emp" items="${list}">
            <tr>
                <td><c:out value="${emp.empid}"/></td>
                <td><c:out value="${emp.empname}"/></td>
                <td><c:out value="${emp.empmail}"/></td>
                <td><c:out value="${emp.department}"/></td>
                <td><c:out value="${emp.manager}"/></td>
                <td><a href="/updateemployeeform?empid=${emp.empid}">Update</a></td>
                <td><a href="/deleteemployee?empid=${emp.empid}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<form method="post" action="home">
    <input type="submit" value="Logout">
</form>
</body>
</html>