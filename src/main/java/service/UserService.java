package service;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

public class UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public void addUser(User user) {
        if(checkUser(user)) {
            new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
        } else {
            userService.update(user);
        }
    }

    public void update(User user) {
        new UserHibernateDAO(sessionFactory.openSession()).update(user);
    }

    public void deleteUser(Long id) {
        try {
            new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAllUsers();
    }

    public boolean checkUser(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).checkUser(user);
    }

}
