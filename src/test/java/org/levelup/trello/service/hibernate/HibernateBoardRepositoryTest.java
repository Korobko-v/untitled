package org.levelup.trello.service.hibernate;

import lombok.SneakyThrows;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.BoardColumn;
import org.levelup.trello.model.User;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HibernateBoardRepositoryTest {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private HibernateBoardRepository boardRepository;

    @BeforeEach
    public void setupRepository() {
        factory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        boardRepository = new HibernateBoardRepository(factory);

        when(factory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);

    }

    @SneakyThrows
    @Test
    public void testCreateBoard_whenAllDataIsValid_thenPersistBoard() {

        Integer userId = 2;
        String name = "name10";
        User user = new User();

        user.setId(userId);
        when(session.load(User.class, userId)).thenReturn(user);
        Board board = boardRepository.createBoard(name, true, userId);
        assertEquals(name, board.getName());
        assertTrue(board.isFavourite());
        assertEquals(userId, board.getOwner().getId());
    }

    @SneakyThrows
    @Test
    public void testDeleteBoard_whenBoardExists() {
        Board board = new Board();
        Integer boardId = 2;
        when(session.get((Board.class), boardId)).thenReturn(board);
        when(session.load((Board.class), boardId)).thenReturn(board);
        board.setBoardId(boardId);

        assertEquals(board, boardRepository.getBoardById(boardId));

        boardRepository.deleteBoard(boardId);
        verify(session).delete(any());
        //      assertNull(boardRepository.getBoardById(boardId));
    }

    @SneakyThrows
    @Test
    public void testUpdateBoard_whenBoardExists() {

        Integer boardId = 8;
        String name = "newTestName";
        boolean favourite = true;
        Integer ownerId = 1;

        Board board = boardRepository.createBoard("oldname", false, 6);
        when(session.get((Board.class), boardId)).thenReturn(board);
        when(session.load((Board.class), boardId)).thenReturn(board);
        board.setBoardId(boardId);
        User user = new User();
        when(session.get((User.class), ownerId)).thenReturn(user);

        user.setId(ownerId);

        board = boardRepository.updateBoard(boardId, name, favourite, ownerId);
        verify(session).update(any());
        assertEquals(board.getBoardId(), boardId);
        assertEquals(board.getName(), name);
        assertTrue(board.isFavourite());
        assertSame(user, board.getOwner());

    }

}