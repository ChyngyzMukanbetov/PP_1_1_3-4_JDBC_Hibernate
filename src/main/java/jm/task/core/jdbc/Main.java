package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Petya", "Petrov", (byte) 7);
        userServiceImpl.saveUser("Oleg", "Olegov", (byte) 10);
        userServiceImpl.saveUser("Dmitrii", "Dmitriev", (byte) 22);
        userServiceImpl.saveUser("Igor", "Igorev", (byte) 36);
        System.out.println(userServiceImpl.getAllUsers().toString());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
        userServiceImpl.closeConnection();
    }
}
