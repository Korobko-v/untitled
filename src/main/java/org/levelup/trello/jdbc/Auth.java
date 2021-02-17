package org.levelup.trello.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Auth {
    public static void main(String[] args) {
        JdbcConnectionService connectionService = new JdbcConnectionService();
        try (Connection connection = connectionService.openConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите логин:");
            String log = reader.readLine();
            Boolean exists = false;
            while (rs.next()) {
//                Integer id = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
//                String email = rs.getString(4);
//                String name = rs.getString(5);

                if (log.equals(login)) {
                    exists = true;
                    System.out.println("Введите пароль:");
                    String pas = reader.readLine();
                    if (pas.equals(password)) {
                        System.out.println("Вход выполнен");
                    }
                    else {
                        System.out.println("Неверный пароль");
                    }
                }
            }
            if (!exists) {
                System.out.println("Пользователя с логином " + log + " не существует");
            }
            reader.close();
        }

        catch (SQLException | IOException exc) {
            throw new RuntimeException(exc);

        }
    }
}

