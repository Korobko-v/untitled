package org.levelup.trello;

import org.levelup.trello.jdbc.JdbcConnectionService;
import org.levelup.trello.service.UserService;
import org.levelup.trello.service.jdbc.JdbcUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class TrelloApp {
    static String userLogin = "";
    static  int userId = 0;
    public static void main(String[] args) throws IOException, SQLException {

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

    public static void signUp() throws IOException, SQLException {
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
        System.out.println("1: Вход с использованием текущего логина и пароля");
        System.out.println("2: Вход в другую учётную запись");
        System.out.println("Другая клавиша: Выход");

        String s = reader.readLine();

        if (s.equals("1")) {
            logIn(login, password);
        }
        else if (s.equals("2")) {
            logIn();
        }

    }

    public static void logIn() throws IOException, SQLException {
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

    public static void logIn(String login, String password) throws IOException, SQLException {
        usersMenu();
    }

    public static void usersMenu() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1: Управление досками");
        System.out.println("2: Войти в другую учётную запись");
        System.out.println("3: Зарегистрировать нового пользователя");
        System.out.println("4: Удалить пользователя");
        System.out.println("Другая клавиша: Выход");
        String s = reader.readLine();

        if (s.equals("1")) {
            editBoards();
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
                main(new String[0]);
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }

    }

    public static void editBoards() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[" + userLogin + "] " + "1: Добавить доску");
        System.out.println("[" + userLogin + "] " + "2: Изменить доску");
        System.out.println("[" + userLogin + "] " + "3: Удалить доску");
        System.out.println("[" + userLogin + "] " + "4: Показать все мои доски");
        System.out.println("[" + userLogin + "] " + "Другая клавиша: Выход");
        String s = reader.readLine();
        if (s.equals("1")) {
            try (Connection connection = new JdbcConnectionService().openConnection()) {
                System.out.println("Введите название доски");
                String boardName = reader.readLine();

                String sqlAddBoard = "insert into boards (board_id, name, favourite, owner_id)" +
                       "values (nextval('boards_board_id_seq'), ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sqlAddBoard, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, boardName);
                statement.setBoolean(2, false);
                statement.setInt(3, userId);

                statement.executeUpdate();

                System.out.println("Доска добавлена");
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }

        if (s.equals("2")) {
            try (Connection connection = new JdbcConnectionService().openConnection()) {
                System.out.println("Введите ID редактируемой доски: ");
                Integer idToEdit = Integer.parseInt(reader.readLine());
                System.out.println("Введите новое название доски: ");
                String newName = reader.readLine();
                String sql = "update boards set name = " + "'" + newName + "' " + "where board_id = " + idToEdit + "and owner_id = " + userId;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Доска изменена");
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }

        if (s.equals("3")) {
            try (Connection connection = new JdbcConnectionService().openConnection()) {
                System.out.println("Введите ID удаляемой доски: ");
                Integer idToDelete = Integer.parseInt(reader.readLine());
                String sql = "delete from boards where board_id = " + idToDelete + "and owner_id = " + userId;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Доска удалена");
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }

        if (s.equals("4")) {
            try (Connection connection = new JdbcConnectionService().openConnection()) {
                String sql = "select * from boards where owner_id = " + userId;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Integer board_id = rs.getInt(1);
                    String name = rs.getString(2);
                    Boolean favourite = rs.getBoolean(3);
                    System.out.println(String.join("|", board_id.toString(), name, favourite.toString()));
                }
            }
            catch (SQLException exc) {
                System.out.println("Ошибка при работе с базой. " + exc.getMessage());
                throw new RuntimeException(exc);
            }
        }
    }
}
