<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8/>
    <title>Регистрация</title>
</head>
<body>
<div align="center">
    <form action="/doregistration" method="post">
        <ul>
            <li><label>Login</label>
                <input type="text" name="login" required></li>
            <li><label>Name</label>
                <input type="text" name="name" required></li>
            <li><label>Password</label>
                <input type="password" name="pass" required></li>
            <li>
                <input type="submit" value="Регистрация"></li>
        </ul>
    </form>
</div>
<div align="center">
    <a href="/">Назад</a>
</div>

</body>
</html>