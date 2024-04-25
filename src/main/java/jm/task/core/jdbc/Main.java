package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
//        User user1 = new User("Ivan", "Sidorov", (byte)32);
//        User user2 = new User("Dmitriy", "Kruglov", (byte)13);
//        User user3 = new User("Oleg", "Kim", (byte)28);
//        User user4 = new User("Oksana", "Trav", (byte)14);
        userDaoJDBC.saveUser("Ivan", "Sidorov", (byte)32);
        userDaoJDBC.saveUser("Dmitriy", "Kruglov", (byte)13);
        userDaoJDBC.saveUser("Oleg", "Kim", (byte)28);
        userDaoJDBC.saveUser("Oksana", "Trav", (byte)14);
        userDaoJDBC.getAllUsers().forEach(System.out::println);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }
}
