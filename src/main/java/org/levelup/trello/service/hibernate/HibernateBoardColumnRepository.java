package org.levelup.trello.service.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.levelup.trello.service.BoardColumnRepository;

import java.util.Collection;

public class HibernateBoardColumnRepository extends AbstractHibernateRepository implements BoardColumnRepository {
    public HibernateBoardColumnRepository(SessionFactory factory) {
        super(factory);
    }

    public BoardColumn addColumn(String name, Integer columnOrder, Integer boardId) {
        return runWithTransaction(session -> {
            BoardColumn column = new BoardColumn();
            column.setOrder(columnOrder);
            column.setName(name);
            column.setBoard(session.load(Board.class, boardId));
            session.persist(column);
            System.out.println("Колонка добавлена");
            return column;
        });
    }

    @Override
    public BoardColumn updateColumn(Integer id, String name, Integer columnOrder, Integer boardId) {
        return runWithTransaction(session -> {
            BoardColumn column = session.load(BoardColumn.class, id);
            column.setName(name);
            column.setOrder(columnOrder);
            column.setBoard(session.get(Board.class, boardId));
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

    public BoardColumn getColumnById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(BoardColumn.class, id);
        }
    }
}
