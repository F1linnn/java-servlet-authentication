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


@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        User user = userDAO.findByEmail(email);




        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("welcome.jsp");
        } else {
            request.setAttribute("error", "Email или пароль введены неверно!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

