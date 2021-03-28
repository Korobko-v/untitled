package org.levelup.trello.service;

import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;

public interface BoardColumnRepository {
    BoardColumn updateColumn(Integer id, String name, Integer columnOrder, Integer boardId);
    BoardColumn deleteColumn(Integer id);
    BoardColumn findColumnByName(String name);
}
