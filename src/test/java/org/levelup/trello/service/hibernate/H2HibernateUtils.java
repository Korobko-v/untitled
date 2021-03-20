package org.levelup.trello.service.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.levelup.trello.model.*;

import java.util.Properties;

public class H2HibernateUtils {
    private static SessionFactory factory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.url" , "jdbc:h2:mem:trello;INIT=create schema if not exists trello");
        hibernateProperties.setProperty("hibernate.connection.username" , "sa");
        hibernateProperties.setProperty("hibernate.connection.password" , "");
        hibernateProperties.setProperty("hibernate.connection.driver_class" , "org.h2.Driver");

        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        Configuration configuration = new Configuration();
        return configuration
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Board.class)
                .addAnnotatedClass(UserCredentials.class)
                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Card.class)
               .addAnnotatedClass(BoardColumn.class)
              .addAnnotatedClass(Team.class)
//                .addAnnotatedClass(TeamMember.class)
//                .addAnnotatedClass(Test.class)
//                .addAnnotatedClass(UserProfile.class)
                .buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
