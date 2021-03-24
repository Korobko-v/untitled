package org.levelup.trello.service.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.levelup.trello.service.BoardRepository;

import javax.persistence.Column;
import java.util.Collection;

public class HibernateBoardRepository extends AbstractHibernateRepository implements BoardRepository {

    public HibernateBoardRepository (SessionFactory factory) {
        super(factory);
    }

    @Override
    public Board createBoard(String name, boolean favourite, Integer userId) {
       return runWithTransaction(session -> {
                Board board = new Board();
                board.setName(name);
                board.setFavourite(favourite);
                board.setOwner(session.load(User.class, userId));
                session.persist(board);
            return board;
        });
    }


    public Board updateBoard(Integer boardId, String name, boolean favourite, Integer ownerId) {
        return runWithTransaction(session -> {
            Board board = session.load(Board.class, boardId);
            board.setName(name);
            board.setFavourite(favourite);
            board.setOwner(session.get(User.class, ownerId));
            session.update(board);
            return board;
        });
    }
    public Board getBoardById(Integer boardId) {
        try (Session session = factory.openSession()) {
            return session.get(Board.class, boardId);
        }
    }

    public Board deleteBoard(Integer boardId) {
        return runWithTransaction(session -> {
            Board board = session.load(Board.class, boardId);
            session.delete(board);
            System.out.println("Доска " + boardId + " удалена.");
            return board;
        });
    }
}
