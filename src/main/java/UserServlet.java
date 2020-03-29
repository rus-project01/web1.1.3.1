import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;
import service.UserService;

@WebServlet("/serv/*")
public class UserServlet extends HttpServlet {
        UserService servletUser = new UserService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArrayList<User> user = null;
            try {
                servletUser.createTable();
                user = servletUser.getAllUsers();
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

            @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            User user = new User();
            ArrayList<User> users = null;
            user.setName(req.getParameter("name"));
            user.setPassword(req.getParameter("password"));
            user.setMoney(Long.parseLong(req.getParameter("money")));
            try {
                servletUser.checkUser(user);
                users = servletUser.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("user", users);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
