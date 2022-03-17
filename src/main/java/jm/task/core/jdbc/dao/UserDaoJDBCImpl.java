package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT(8) not NULL AUTO_INCREMENT, " +
                " name TEXT NOT NULL, " +
                " lastName TEXT NOT NULL, " +
                " age TINYINT(1) NOT NULL, " +
                " PRIMARY KEY (id))";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.executeUpdate();
            System.out.println("Table successfully created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users ";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.executeUpdate();
            System.out.println("Table successfully dropped...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) " +
                "values (?, ?, ?)";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setByte(3, age);
            preStmt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ? ";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.setLong(1, id);
            preStmt.executeUpdate();
            System.out.println("User с id – " + id + " удален из базы данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM users";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = preStmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users ";
        try (PreparedStatement preStmt = connection.prepareStatement(sql)) {
            preStmt.executeUpdate();
            System.out.println("Table successfully cleaned...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
