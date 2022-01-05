<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>reg</title>
</head>
<body>
<form method="post" action="/controller">
    <input type="text" maxlength="30" name="login" placeholder="login" required>
    <input type="password" maxlength="30" name="password" placeholder="pass" required>
    <input type="email" maxlength="40" name="email" placeholder="email" required>
    <input type="text" maxlength="30" name="first_name" placeholder="first name" required>
    <input type="text" maxlength="30" name="last_name" placeholder="last name" required>
    <button type="submit" name="command" value="registration">submit</button>
</form>
</body>
</html>
