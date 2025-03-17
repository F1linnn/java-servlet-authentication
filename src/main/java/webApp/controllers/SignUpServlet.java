package webApp.controllers;

import org.mindrot.jbcrypt.BCrypt;
import webApp.dao.UserDAO;
import webApp.model.User;
import webApp.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        // Проверка на наличие уже зарегистрированного email
        if (userDAO.findAll().stream().anyMatch(user -> user.getEmail().equals(email))) {
            request.setAttribute("error", "Email уже зарегистрирован");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Проверка формата email
        if (!isValidEmail(email)) {
            request.setAttribute("error", "Некорректный формат email");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Проверка сложности пароля
        if (!isValidPassword(pass)) {
            request.setAttribute("error", "Пароль должен содержать хотя бы 8 символов, включая заглавные буквы и цифры");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());

        // Создание нового пользователя
        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userDAO.save(user);

        // Создание сессии и перенаправление на страницу приветствия
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        response.sendRedirect("welcome.jsp");
    }

    @Override
    public void destroy() {
        HibernateUtil.shutdown();
    }

    // Метод для проверки формата email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Метод для проверки сложности пароля
    private boolean isValidPassword(String password) {
        // Пароль должен содержать хотя бы 8 символов, включая заглавные буквы и цифры
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
