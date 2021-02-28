package org.levelup.trello.jdbc;

import org.levelup.trello.profiling.AliveTime;
import org.levelup.trello.profiling.OpenConnectionServiceInvocationHandler;
import org.levelup.trello.profiling.Profiling;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionService implements ConnectionService {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionService buildJdbcConnectionService() {
        //SomeInterface
        //doSmth();

        //A, ProxyS, S
        // A использует внутри себя класс S

        // А думает, что использует S, но на самом деле работает через ProxyS

        return (ConnectionService) Proxy.newProxyInstance(JdbcConnectionService.class.getClassLoader(),
                JdbcConnectionService.class.getInterfaces(),
                new OpenConnectionServiceInvocationHandler(new JdbcConnectionService()));
    }

    @AliveTime
    @Override
    @Profiling
    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/trello",
                "postgres", "1234");
    }
}
