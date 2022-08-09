<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee management system</title>
</head>
<body>
<form method="post" action="login">
    <input type="text" placeholder="User Name" name="username"><br><br>
    <input type="password" placeholder="Password" name="password"><br><br>
    <input type="submit" value="Login"><br><br>
</form>
<form method="post" action="newuser">
    <br><br>Register below for new user<br><br>
    <input type="text" placeholder="User Name (New)" name="username"><br><br>
    <input type="password" placeholder="Password (New)" name="password"><br><br>
    <input type="submit" value="New User">
</form>
</body>
</html>
