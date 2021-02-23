package org.levelup.trello;

import org.levelup.trello.jdbc.JdbcConnectionService;
import org.levelup.trello.service.UserService;
import org.levelup.trello.service.jdbc.JdbcUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;


public class Trello {
    static String userLogin = "";
    static  int userId = 0;

    public void start() throws IOException, SQLException {
        System.out.println("1: Регистрация");
        System.out.println("2: Авторизация");
        System.out.println("Другая клавиша: Выход");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        if (s.equals("1")) {
            signUp();
        }

        else if (s.equals("2")) {
            logIn();
        }
    }

    public void signUp() throws IOException {
        try (Connection connection = new JdbcConnectionService().openConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите логин");
            String login = reader.readLine();

            System.out.println("Введите имя");
            String name = reader.readLine();

            System.out.println("Введите email");
            String email = reader.readLine();

            System.out.println("Введите пароль");
            String password = reader.readLine();


            UserService userService = new JdbcUserService();
            userService.createUser(login, email, name, password);
            userLogin = login;

            String sqlLogin = "select * from users where login = '" + login + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlLogin);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            userId = id;

            System.out.println("1: Вход с использованием текущего логина и пароля");
            System.out.println("2: Вход в другую учётную запись");
            System.out.println("Другая клавиша: Выход");

            String s = reader.readLine();

            if (s.equals("1")) {
                logIn(login, password);
            } else if (s.equals("2")) {
                logIn();
            }
        }
        catch (SQLException exc) {
            System.out.println("Ошибка при работе с базой " + exc.getMessage());
            throw new RuntimeException(exc);
        }

    }

    public void logIn() throws IOException, SQLException {
        try (Connection connection = new JdbcConnectionService().openConnection()) {
            System.out.println("Введите логин");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            userLogin = s;
            String sqlLogin = "select * from users where login = '" + s + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlLogin);
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            userId = id;
            String sqlPassword = "select * from user_credentials where user_id = " + id;
            ResultSet rsPass = stmt.executeQuery(sqlPassword);
            System.out.println("Введите пароль: ");
            String pass = reader.readLine();

            String correctPass = "";
            while (rsPass.next()) {
                correctPass = rsPass.getString(2);
            }

            if (pass.equals(correctPass)) {
                System.out.println("Вход выполнен.");
                usersMenu();
            }
            else {
                System.out.println("Неверный логин или пароль");
                logIn();
            }

        }
        catch (SQLException exc) {
            System.out.println("Ошибка при работе с базой " + exc.getMessage());
            throw new RuntimeException(exc);
        }
    }

    private void logIn(String login, String password) throws IOException, SQLException {
        usersMenu();
    }

    public void usersMenu() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1: Управление досками");
        System.out.println("2: Войти в другую учётную запись");
        System.out.println("3: Зарегистрировать нового пользователя");
        System.out.println("4: Удалить пользователя");
        System.out.println("Другая клавиша: Выход");
        String s = reader.readLine();

        if (s.equals("1")) {
            BoardService.editBoards(userLogin);
        }

        if (s.equals("2")) {
            logIn();
        }

        if (s.equals("3")) {
            signUp();
        }

        if (s.equals("4")) {
            try (Connection connection = new JdbcConnectionService().openConnection()) {
                String ucSql = "delete from user_credentials where user_id = " + userId;
                String sql = "delete from users where id = " + userId;
                Statement statement = connection.createStatement();
                statement.executeUpdate(ucSql);
                statement.executeUpdate(sql);
                System.out.println("Пользователь удалён");
                start();
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }
    }
}