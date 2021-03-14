package org.levelup.trello.service.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.trello.model.User;
import org.levelup.trello.service.UserRepository;

import java.util.List;

@RequiredArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final SessionFactory factory;

    @Override
    public User createUser(String login, String email, String name, String password) {
        try (Session session = factory.openSession()) { //открываем новое соединение к базе
            Transaction tx = session.beginTransaction();
            User user = new User(null, name, login, email);
            session.persist(user); //добавление пользователя в таблицу users -> insert into users

            tx.commit();
            return user;
        }
    }

    @Override
    public User findUserByLogin(String login) {
        try (Session session = factory.openSession()) {
            List<User> users = session.createQuery("from user where login = :login", User.class)
                    .setParameter("login", login)
                    .getResultList();
            return  users.isEmpty() ? null: users.get(0);
        }
    }

    @Override
    public List<User> findUsersByName(String name) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from User where name = :username", User.class)
                    .setParameter("username", name)
                    .getResultList();
        }
    }

    @Override
    public List<User> findUsersByIds(List<Integer> userIds) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from User where id in (:ids)", User.class)
                    .setParameter("ids", userIds)
                    .getResultList();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            //createQuery - HQL Hibernate Query Language
            return session.createQuery("from User", User.class)
                    .getResultList();
        }
    }

    //get()
    //load()

    public User getUserById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public User loadUserById(Integer id) {
        try (Session session = factory.openSession()) {
            User user = session.load(User.class, id);
            System.out.println("User is loaded in repository");
            //user.getName();
            return user;
        }
    }
}
