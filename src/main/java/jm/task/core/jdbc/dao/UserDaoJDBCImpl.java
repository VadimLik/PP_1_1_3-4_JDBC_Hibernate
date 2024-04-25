package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    private static final String INSERT_NEW_USER = "insert into myfirstconnect.users (id, name, lastname, age) values(?,?,?,?)";

    private static final String SELECT_ALL_USERS = "select id, name, lastname, age from myfirstconnect.users";

    private static final String DELETE_USER_BY_ID = "delete from myfirstconnect.users where id = ?";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS myfirstconnect.users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45),  age INT(3))";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS myfirstconnect.users";

    private static final String USE_MYSQL = "use mysql";



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();

             Statement statement = connection.createStatement()) {

            statement.executeUpdate(USE_MYSQL);

            statement.executeUpdate(CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();

             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();

             PreparedStatement ps = connection.prepareStatement(INSERT_NEW_USER)) {

            ps.setLong(1, getAllUsers().size() + 1);

            ps.setString(2, name);

            ps.setString(3, lastName);

            ps.setByte(4, age);

           ps.executeUpdate();

            System.out.printf("User с именем - %s добавлен в базу данных \n", name);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();

            PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID)) {

            ps.setLong(1, id);

            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection();

             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((byte)resultSet.getLong("age"));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.getConnection();

             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);

            while (resultSet.next()) {

                removeUserById(resultSet.getLong("id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
