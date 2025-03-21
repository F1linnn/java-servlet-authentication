package webApp.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Удаляем объект user из сессии
        HttpSession session = request.getSession();
        session.invalidate(); // уничтожаем сессию

        // Перенаправляем на страницу входа
        response.sendRedirect("index.jsp");
    }
}

