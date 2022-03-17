package jm.task.core.jdbc.util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    //public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/world?integratedSecurity=true;";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Madara2631!";

    public Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            //System.out.printf("connection is ok " + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection ERROR");
        } //catch (ClassNotFoundException e) {
            //e.printStackTrace();
       // }
        return connection;
    }
}
