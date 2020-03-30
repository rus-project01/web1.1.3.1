package Servlets;

import model.User;
import service.UserService;
import util.DBHelper;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    UserService servletUser = new UserService(DBHelper.getSessionFactory());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        servletUser.deleteUser(id);
        response.sendRedirect("/serv");
    }
}  