package org.levelup.trello.service.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.levelup.trello.service.BoardRepository;

public class HibernateBoardRepository extends AbstractHibernateRepository implements BoardRepository {

    public HibernateBoardRepository (SessionFactory factory) {
        super(factory);
    }

    @Override
    public Board createBoard(Integer userId, String name, boolean favourite) {
       return runWithTransaction(session -> {
                Board board = new Board();
                board.setName(name);
                board.setFavourite(favourite);
                board.setOwner(session.load(User.class, userId));
                session.persist(board);
            return board;
        });
    }

    public Board addColumn(Integer boardId, String name, int columnOrder) {
        return runWithTransaction(session -> {
            BoardColumn column = new BoardColumn();
            column.setOrder(columnOrder);
            Board board = session.get(Board.class, boardId);
            board.getColumns().add(column);

            return board;
        });
    }
}
