package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users(name, password, money) values (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getMoney());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where name = ?");
            ps.setString(1, user.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                updateUser(user);
            } else {
                addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("update users set name=?, password=?, money=? where name=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setLong(3, user.getMoney());
            ps.setString(4, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setMoney(rs.getLong("money"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(Long id) throws SQLException {
        User user = new User();
        PreparedStatement ps = connection.prepareStatement("select * from users where id=?");
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setMoney(rs.getLong("money"));
        }
        return user;
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists Users (id bigint auto_increment, name varchar(256), password varchar(256), money bigint, primary key (id))");
        stmt.close();
    }
}
