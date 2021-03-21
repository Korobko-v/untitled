package org.levelup.trello.service;

import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;

public interface BoardRepository {
    Board createBoard(String name, boolean favourite, Integer userId);
}
