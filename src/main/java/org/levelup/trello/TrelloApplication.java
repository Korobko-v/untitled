package org.levelup.trello;

import org.hibernate.SessionFactory;
import org.levelup.trello.hibernate.HibernateUtils;
import org.levelup.trello.model.Student;
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


        System.out.println("Введите имя");
        String stName = reader.readLine();

        System.out.println("Введите рейтинг");
        Integer rating = Integer.parseInt(reader.readLine());

        HibernateStudentRepository studentRepository = new HibernateStudentRepository(factory);
        Student student = studentRepository.createStudent(stName, rating);
        System.out.println("Студент добавлен");

        factory.close();
    }
}
