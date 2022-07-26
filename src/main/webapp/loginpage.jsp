<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee management system</title>
</head>
<body>
<form method="post" action="profile">
    User name : <input type="text" name="username"><br><br>
    Password : <input type="password" name="password"><br><br>
    <input type="submit" value="Login"><br><br>
</form>
<form method="post" action="newuser">
    Register below for new user<br><br>
    User name (New): <input type="text" name="username"><br><br>
    Password (New): <input type="password" name="password"><br><br>
    <input type="submit" value="New User">
</form>
</body>
</html>
