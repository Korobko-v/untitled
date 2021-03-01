package org.levelup.trello.jdbc;

import org.levelup.trello.profiling.Profiling;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionService {
    @Profiling
    Connection openConnection() throws SQLException;

    @Profiling
    void closeConnection() throws SQLException;
}
