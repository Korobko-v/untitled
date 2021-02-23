package org.levelup.trello;

import org.levelup.trello.jdbc.JdbcConnectionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import static org.levelup.trello.Trello.userId;

public class BoardService {
    public static void editBoards(String userLogin) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[" + userLogin + "] " + "1: Добавить доску");
        System.out.println("[" + userLogin + "] " + "2: Изменить доску");
        System.out.println("[" + userLogin + "] " + "3: Удалить доску");
        System.out.println("[" + userLogin + "] " + "4: Показать все мои доски");
        System.out.println("[" + userLogin + "] " + "Другая клавиша: Выход");
        String s = reader.readLine();
        if (s.equals("1")) {
            addBoard();
        }

        if (s.equals("2")) {
            editBoard();
        }

        if (s.equals("3")) {
            deleteBoard();
        }

        if (s.equals("4")) {
            showBoards();
        }
    }
    private static void addBoard()  throws IOException {
        try (Connection connection = new JdbcConnectionService().openConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    private static void editBoard() throws IOException {
        try (Connection connection = new JdbcConnectionService().openConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    private static void deleteBoard() throws IOException {
        try (Connection connection = new JdbcConnectionService().openConnection()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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

    private static void showBoards() {
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
