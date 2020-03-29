package service;

import dao.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserService {

    public void addUser(User user) throws SQLException {
        getBankClientDAO().addUser(user);
    }

    public void checkUser(User user) throws SQLException {
        getBankClientDAO().checkUser(user);
    }

    public void deleteUser(Long id) throws SQLException {
        getBankClientDAO().deleteUser(id);
    }

    public void updateUser(User user) throws SQLException {
        getBankClientDAO().updateUser(user);
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        return getBankClientDAO().getAllUsers();
    }

    public User getUserById(Long id) throws SQLException {
        return getBankClientDAO().getUserById(id);
    }

    public void createTable() {
        UserDAO dao = getBankClientDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("web5?").          //db name
                    append("user=root&").          //login
                    append("password=Qwerty12").       //password
                    append("&serverTimezone=UTC").
                    append("&useSSL=false");

            System.out.println("URL: " + url + "\n");


            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getBankClientDAO() {
        return new UserDAO(getMysqlConnection());
    }

}
