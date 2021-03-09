package org.levelup.trello.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
public class HibernateUtils {

    private static SessionFactory factory = buildSessionFactory();
    private HibernateUtils() {}

    private static SessionFactory buildSessionFactory() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.connection.url" , "jdbc:postgresql://localhost:5432/trello");
        hibernateProperties.setProperty("hibernate.connection.username" , "postgres");
        hibernateProperties.setProperty("hibernate.connection.password" , "1234");
        hibernateProperties.setProperty("hibernate.connection.driver_class" , "org.postgresql.Driver");

        hibernateProperties.setProperty("show_sql", "true");
        hibernateProperties.setProperty("format_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "validate");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(hibernateProperties)
                .build();

        Configuration configuration = new Configuration();
        return configuration.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
