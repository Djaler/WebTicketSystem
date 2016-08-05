<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8/>
    <link href="/css/master.css" rel="stylesheet" type="text/css"/>
    <title>Вход</title>
</head>
<body>
<div align="center">
    <form action="/login" method="post">
        <ul>
            <li><label>Login</label>
                <input type="text" name="login" required></li>
            <li><label>Password</label>
                <input type="password" name="pass" required></li>
            <li>
                <input type="submit" value="Войти"></li>
        </ul>
    </form>
</div>
<div align="center">
    <a href="/registration">Регистрация</a>
</div>

</body>
</html>