package org.levelup.trello;

import org.hibernate.SessionFactory;
import org.levelup.trello.hibernate.HibernateUtils;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.Student;
import org.levelup.trello.service.hibernate.HibernateBoardColumnRepository;
import org.levelup.trello.service.hibernate.HibernateBoardRepository;
import org.levelup.trello.service.hibernate.HibernateStudentRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrelloApplication {
    public static void main(String[] args) throws IOException {

        SessionFactory factory = HibernateUtils.getFactory();

//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите логин");
//        String login = reader.readLine();
//
//        System.out.println("Введите имя");
//        String name = reader.readLine();
//
//        System.out.println("Введите email");
//        String email = reader.readLine();
//
//        System.out.println("Введите пароль");
//        String password = reader.readLine();
//
//
//        UserRepository userRepository = new HibernateUserRepository(factory);
//        User user = userRepository.createUser(login, email, name, password);
//        System.out.println(user);

//        HibernateUserRepository hibernateUserRepository = new HibernateUserRepository(factory);
//        List<User> users = hibernateUserRepository.findUsersByName("Dmitry");
//        users.forEach(user -> System.out.println(user));
//
//        System.out.println();
//
//        User getUser = hibernateUserRepository.getUserById(510);
//        System.out.println(getUser);
//
//        System.out.println();
//
//        User loadUser = hibernateUserRepository.loadUserById(5);
//        System.out.println(loadUser);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        System.out.println("Введите название");
//        String name = reader.readLine();
//
//        System.out.println("favourite (true/false)");
//        Boolean favourite = Boolean.valueOf(reader.readLine());
//
//        System.out.println("Введите id владельца");
//        int userId = Integer.parseInt(reader.readLine());
//
//        HibernateBoardsRepository hibernateBoardsRepository = new HibernateBoardsRepository(factory);
//        Board board = hibernateBoardsRepository.createBoard(name, favourite, userId);
//        System.out.println("Доска добавлена в таблицу");


//        System.out.println("Введите имя");
//        String stName = reader.readLine();
//
//        System.out.println("Введите рейтинг");
//        Integer rating = Integer.parseInt(reader.readLine());
//
//        HibernateStudentRepository studentRepository = new HibernateStudentRepository(factory);
//        Student student = studentRepository.createStudent(stName, rating);
//        System.out.println("Студент добавлен");

//        Board board = boardRepository.updateBoard(5, "rootBoard", true, 2);
//        System.out.println("Доска изменена");
//
//        System.out.println("Введите id удаляемой доски");
//        String s = reader.readLine();
//        Board del = boardRepository.deleteBoard(Integer.parseInt(s));
//        System.out.println("Доска " + s + " удалена");


//        HibernateBoardColumnRepository columnRepository = new HibernateBoardColumnRepository(factory);
//        BoardColumn boardColumn = columnRepository.updateColumn(1, "oneCol", 1);
//        System.out.println("Колонка изменена.");

//        Board board = boardRepository.createBoard(2, "name6", true);
//        System.out.println("Доска создана");
//        System.out.println(board.getOwner().getId());


//        System.out.println("Введите id удаляемой колонки: ");
//        String s = reader.readLine();
//        BoardColumn column = columnRepository.deleteColumn(Integer.parseInt(s));
//        System.out.println("Колонка " + s + " удалена.");
        HibernateBoardColumnRepository columnRepository = new HibernateBoardColumnRepository(factory);
        columnRepository.deleteColumn(3);
        factory.close();
    }
}
