package dao;
import model.*;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.EntityMode;
import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class UserHibernateDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> cars = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return cars;
    }

    public void deleteUser(Long id) throws SQLException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        List<User> cars = query.list();
        session.delete(cars.get(0));
        transaction.commit();
    }

    public void addUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
    }

    public void update(User user) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set password = :password, money = :money where name = :name");
        query.setParameter("password", user.getPassword());
        query.setLong("money", user.getMoney());
        query.setParameter("name", user.getName());
        query.executeUpdate();
        transaction.commit();
    }

    public boolean checkUser(User user) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name = :name");
        query.setParameter("name", user.getName());
        List<User> cars = query.list();
        transaction.commit();
        return cars.size() == 0;
    }
}
