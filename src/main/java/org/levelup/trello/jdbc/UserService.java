package org.levelup.trello.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public void printUsers() {
        JdbcConnectionService connectionService = new JdbcConnectionService();
        try (Connection connection = connectionService.openConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
               Integer id = rs.getInt(1);
               String login = rs.getString(2);
               String email = rs.getString(3);
               String name = rs.getString(4);

                System.out.println(String.join("|", id.toString(), login,
                        email,name));
            }
        }
        catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
    }
}
