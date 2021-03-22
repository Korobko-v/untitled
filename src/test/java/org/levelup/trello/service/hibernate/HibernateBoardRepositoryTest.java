package org.levelup.trello.service.hibernate;

import lombok.SneakyThrows;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.levelup.trello.model.Board;
import org.levelup.trello.model.User;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        //when
        Integer userId = 2;


        User user = new User();

        user.setId(userId);
        when(session.load(User.class, userId)).thenReturn(user);
        Board board = boardRepository.createBoard("name10", true, userId);
        assertEquals("name10",board.getName());
        assertTrue(board.isFavourite());
        assertEquals(userId, board.getOwner().getId());
//        ArgumentCaptor<Board> boardArgumentCaptor = ArgumentCaptor.forClass(Board.class);
//        Mockito.verify(session).persist(boardArgumentCaptor.capture());
//        Mockito.verify(transaction).commit();
//
//        Assertions.assertSame(board, boardArgumentCaptor.getValue());
    }
}