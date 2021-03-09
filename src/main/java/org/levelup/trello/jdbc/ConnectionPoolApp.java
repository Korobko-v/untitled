package org.levelup.trello.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolApp {
    public static void main(String[] args) throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("postgres");
        ds.setPassword("1234");
        ds.setUrl("jdbc:postgresql://localhost:5432/trello");
        ds.setMaxActive(4);
        ds.setMaxIdle(4);
        Connection c1 = ds.getConnection();
        Connection c2 = ds.getConnection();
        Connection c3 = ds.getConnection();

        try (Statement statement = ds.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                System.out.println(String.join("|", String.valueOf(rs.getInt(1)), rs.getString(2)
                        , rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println("Все соединения заняты");
        }

    }
}
