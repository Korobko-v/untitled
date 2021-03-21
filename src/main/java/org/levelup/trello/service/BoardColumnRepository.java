package org.levelup.trello.service;

import org.levelup.trello.model.BoardColumn;

public interface BoardColumnRepository {
    BoardColumn updateColumn(Integer id, String name, Integer columnOrder);
    BoardColumn deleteColumn(Integer id);
}
