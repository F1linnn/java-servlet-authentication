<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация</title>
    <!-- Подключение внешнего CSS -->
    <link rel="stylesheet" href="resources/css/index.css">
</head>
<body>
    <div class="container">
        <h2>Авторизация</h2>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <p class="error" style="color: red;"><%= error %></p>
        <%
            }
        %>

        <form action="signin" method="POST">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required><br>

            <button type="submit">Войти</button>
        </form>

        <p>Еще не зарегистрированы? <a href="signup.jsp">Зарегистрироваться</a></p>
    </div>
</body>
</html>
