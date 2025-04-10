<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="webApp.model.User" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="resources/css/welcome.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
    <div class="welcome-container">
        <h2>Добро пожаловать, <%= user.getFullName() %>!</h2>
        <p>Вы успешно авторизованы.</p>
        <a href="logout" class="logout-button">Выйти</a>
    </div>
</body>
</html>
