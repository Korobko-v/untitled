package org.levelup.trello;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

public class TrelloApp {
    public static void main(String[] args) throws IOException, SQLException {
        Trello trello = new Trello();
        Field[] fields = trello.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getModifiers());
        }
//        trello.start();
    }
}
