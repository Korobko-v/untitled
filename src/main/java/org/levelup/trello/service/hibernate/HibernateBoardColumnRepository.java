package org.levelup.trello.service.hibernate;

import org.hibernate.SessionFactory;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.levelup.trello.service.BoardColumnRepository;

public class HibernateBoardColumnRepository extends AbstractHibernateRepository implements BoardColumnRepository {
    public HibernateBoardColumnRepository(SessionFactory factory) {
        super(factory);
    }

    @Override
    public BoardColumn updateColumn(Integer id, String name, Integer columnOrder) {
        return runWithTransaction(session -> {
            BoardColumn column = session.load(BoardColumn.class, id);
            column.setName(name);
            column.setOrder(columnOrder);
            session.update(column);
            return column;
        });
    }

    @Override
    public BoardColumn deleteColumn(Integer id) {
        return runWithTransaction(session -> {
            BoardColumn column = session.load(BoardColumn.class, id);
            session.delete(column);
            return column;
        });
    }
}
