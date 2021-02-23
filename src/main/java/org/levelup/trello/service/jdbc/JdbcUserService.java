package org.levelup.trello.service.jdbc;

import org.levelup.trello.jdbc.JdbcConnectionService;
import org.levelup.trello.model.User;
import org.levelup.trello.service.UserService;

import java.sql.*;

public class JdbcUserService implements UserService {
    private final JdbcConnectionService jdbcConnectionService;

    public JdbcUserService() {
        this.jdbcConnectionService = new JdbcConnectionService();
    }

    @Override
    public User createUser(String login, String email, String name, String password) {
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

    private void saveUserCredentials(Connection connection, Integer userId, String password) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("insert into user_credentials values (?, ?)");
        stmt.setInt(1, userId);
        stmt.setString(2, password);
        stmt.executeUpdate();
    }

}
