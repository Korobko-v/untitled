package org.levelup.trello.jdbc;

import lombok.SneakyThrows;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolApp {
    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("postgres");
        ds.setPassword("enisah56");
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setMaxActive(4);
        ds.setMaxIdle(4);
        Connection c1 = ds.getConnection();
        Connection c2 = ds.getConnection();
        Connection c3 = ds.getConnection();
        Connection c4 = ds.getConnection();
        long start = System.nanoTime();

        Statement statement = null;
        try {
            statement = ds.getConnection().createStatement();
        } catch (Exception e) {
            System.out.println("ConnectionPool заполнен");
        }
        System.out.println("Введите запрос:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sql = reader.readLine();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(String.join("|", String.valueOf(rs.getInt(1)), rs.getString(2)
                    , rs.getString(3), rs.getString(4)));
        }
        c4.close();
// Connection c5 = ds.getConnection();
// c5.createStatement().executeQuery("select * from users");
    }
}
