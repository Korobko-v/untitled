package org.levelup.trello.service.jdbc;

import lombok.SneakyThrows;
import org.levelup.trello.jdbc.ConnectionService;
import org.levelup.trello.jdbc.JdbcConnectionService;
import org.levelup.trello.model.User;
import org.levelup.trello.service.UserRepository;

import java.sql.*;
import java.util.List;

public class JdbcUserRepository implements UserRepository {
    private final ConnectionService jdbcConnectionService;

    public JdbcUserRepository() {
        this.jdbcConnectionService = JdbcConnectionService.buildJdbcConnectionService();
    }

    @SneakyThrows
    @Override
    public User createUser(String login, String email, String name, String password) {
        long start = System.nanoTime();
        try (Connection connection = jdbcConnectionService.openConnection()) {
            String sql = "insert into users (login, name, email)" +
                    "values(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, login);
            stmt.setString(2, name);
            stmt.setString(3, email);

            int rowsAffected = stmt.executeUpdate(); //количество изменённых строк
            System.out.println(rowsAffected);

            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            int generateId = keys.getInt(1);
            System.out.println("id пользователя: " + generateId);

            saveUserCredentials(connection, generateId, password);
            return new User(generateId, name, login, email); 
        }
        catch (SQLException exc) {
            System.out.println("Ошибка при работе с базой " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findUsersByName(String name) {
        return null;
    }

    @Override
    public List<User> findUsersByIds(List<Integer> userIds) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    private void saveUserCredentials(Connection connection, Integer userId, String password) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("insert into user_credentials values (?, ?)");
        stmt.setInt(1, userId);
        stmt.setString(2, password);
        stmt.executeUpdate();
    }

}
