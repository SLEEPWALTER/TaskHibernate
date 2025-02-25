package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        // Создание таблицы
        userService.createUsersTable();
        userService.saveUser("Alice", "Smith", (byte) 25);
        userService.saveUser("Bob", "Johnson", (byte) 30);
        userService.saveUser("Charlie", "Brown", (byte) 35);
        userService.saveUser("David", "Lee", (byte) 40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        // Удаление таблицы
        userService.dropUsersTable();
    }
}
