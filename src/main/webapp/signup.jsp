<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <!-- Подключение внешнего CSS -->
    <link rel="stylesheet" href="resources/css/index.css">
</head>
<body>
    <div class="container">
        <h2>Регистрация</h2>

        <!-- Вывод ошибок -->
        <%

            String error = (String) request.getAttribute("error");
        %>

        <!-- Форма регистрации -->
        <form action="signup" method="post" accept-charset="UTF-8">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">


            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
            <%
                if (error != null && error.contains("Email уже зарегистрирован")) {
            %>
                <span style="color: red;">Email уже зарегистрирован</span>
            <%
                } else if (error != null && error.contains("Некорректный формат email")) {
            %>
                <span style="color: red;">Некорректный формат email</span>
            <%
                }
            %>


            <label for="pass">Пароль:</label>
            <input type="password" id="pass" name="pass" required>
            <%
                if (error != null && error.contains("Пароль должен содержать")) {
            %>
                <span style="color: red;">Пароль должен содержать хотя бы 8 символов, включая заглавные буквы и цифры</span>
            <%
                }
            %>

            <button type="submit">Зарегистрироваться</button>
        </form>

        <p>Уже есть аккаунт?</p>
        <a href="index.jsp" class="login-button">Войти</a>
    </div>
</body>
</html>
