package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import service.UserService;
import util.DBHelper;

@WebServlet("/serv/*")
public class UserServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<User> user = UserService.getInstance().getAllUsers();
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            Long money = Long.parseLong(req.getParameter("money"));
            UserService.getInstance().addUser(new User(name, password, money));
            req.setAttribute("user", UserService.getInstance().getAllUsers());
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
}
