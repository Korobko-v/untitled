package org.levelup.trello;

import java.io.IOException;
import java.sql.*;

public class TrelloApp {
    public static void main(String[] args) throws IOException, SQLException {
        Trello trello = new Trello();
        trello.start();
    }
}
