package org.levelup.trello.service.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.trello.model.Board;
import org.levelup.trello.service.BoardRepository;

@RequiredArgsConstructor
public class HibernateBoardsRepository implements BoardRepository {
    private final SessionFactory factory;

    @Override
    public Board createBoard(String name, boolean favourite, int ownerId) {
        try (Session session = factory.openSession()) { //открываем новое соединение к базе
            Transaction tx = session.beginTransaction();
            Board board = new Board(null,name, favourite, ownerId);
            session.persist(board);
            tx.commit();
            return board;
        }
    }
}
